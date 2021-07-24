from scrapy.spiders import Spider
from scrapy.selector import Selector
from tutroial.db import save
import scrapy
from tutroial.items import NewsItem


# 虎扑NBA
# 递归获取多条新闻
class NewsList(Spider):
    items = []
    name = "newsList"
    # 过滤爬取的域名
    allowed_domains = ["hupu.com"]

    start_urls = [
        # 首次爬取页面
        "https://nba.hupu.com/"
    ]

    # 爬首页新闻列表
    def parse(self, response):
        # filename = response.url.split("/")[-1]
        # open(filename, 'wb').write(response.body)

        sel = Selector(response)
        sites = sel.xpath('//ul[@class="item-list news-item"]/li')
        for site in sites:
            link = site.xpath('a/@href').extract()[0]
            # 获取到子层URL 递归爬取,内容加入到List中
            yield scrapy.Request(link, callback=self.parse_item)
        # save.savelist(self.items)

    # 爬各条新闻的内容
    def parse_item(self, response):
        # 获取子页面内容
        sel = Selector(response)
        news = NewsItem()
        # 新闻内容,所有P标签下的文本
        content = sel.xpath('//div[@class="artical-main-content"]').extract()[0]
        news['content'] = content.strip()

        # 新闻URL
        news['url'] = response.url.strip()

        # 图片url
        img_url = sel.xpath('//div[@class="artical-importantPic"]/img/@src').extract()[0]
        news['img_url'] = img_url.strip()

        # 新闻标题
        title = sel.xpath('//div[@class="artical-title"]/h1/text()').extract()[0]
        news['title'] = title.strip()

        # 作者
        author = sel.xpath('//*[@id="editor_baidu"]/text()').extract()[0]
        news['author'] = author.strip()

        # 发布时间
        date = sel.xpath('//*[@id="pubtime_baidu"]/text()').extract()[0]
        news['date'] = date.strip()
        self.items.append(news)
        # save.savenews(news)
        print(news['title'])





