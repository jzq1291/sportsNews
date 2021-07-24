from scrapy.spiders import Spider
from scrapy.selector import Selector
from tutroial.items import NewsItem
from tutroial.db import save
import scrapy


# 新浪国际足球
class DmozSpider(Spider):
    items = []
    name = "sinaGlobal"
    # 过滤爬取的域名
    allowed_domains = ["sports.sina.com.cn"]

    start_urls = [
        "http://sports.sina.com.cn/global//"
    ]

    def parse(self, response):
        sel = Selector(response)
        rowsites = sel.xpath('//ul[@class="ul-type1"]')[0]
        rows = rowsites.xpath('li/a/@href').extract()
        for link in rows:
            yield scrapy.Request(link, callback=self.parse_item)
            # print(link)

    def parse_item(self, response):
        news = NewsItem()

        sel = Selector(response)

        # 新闻URL
        news['url'] = response.url.strip()
        # 标题
        title = sel.xpath('//h1[@class="main-title"]/text()').extract()[0]
        news['title'] = title.strip()

        # 发布时间
        date = sel.xpath('//span[@class="date"]/text()').extract()[0]
        news['date'] = date.strip().replace('年', '-').replace('月', '-').replace('日', '')

        # 展示图片
        img_url = sel.xpath('//div[@class="img_wrapper"]/img/@src').extract()[0]
        news['img_url'] = img_url.strip()

        # 内容
        content = sel.xpath('//div[@class="article"]').extract()[0].strip()

        # 去除广告JS
        index = content.rfind('<div id="left_hzh_ad">')
        con = content[0:index] + "</div>"

        # 去除视频JS
        index_video1 = con.find('<!--video-list-->')
        con1 = con[0:index_video1]
        index_video2 = con.find('<!--/video-list-->')
        con2 = con[index_video2:]
        con = con1 + con2

        # 去除微博List
        index_weibo1 = con.find('<!-- 微博列表 -->')
        con1 = con[0:index_weibo1]
        index_weibo2 = con.find('<!-- /微博列表 -->')
        con2 = con[index_weibo2:]
        con = con1 + con2

        news['content'] = con

        news['author'] = ""
        # save.paramTest(news)
        self.items.append(news)
        save.savenews(news)