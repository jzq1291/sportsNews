from scrapy.spiders import Spider
from scrapy.selector import Selector
import scrapy
from tutroial.items import NewsItem


# 虎扑NBA
# 递归获取多条新闻
class NewsList(Spider):
    name = "hupu"
    news = NewsItem()
    # 过滤爬取的域名
    allowed_domains = ["hupu.com"]

    start_urls = [
        # 首次爬取页面
        # 国际足球
        "https://voice.hupu.com/soccer",
        # 中国足球
        "https://voice.hupu.com/china",
        # NBA
        "https://voice.hupu.com/nba",
        # 赛车
        "https://voice.hupu.com/f1"
    ]

    # 爬首页最新 新闻列表
    def parse(self, response):

        sel = Selector(response)
        rowsites = sel.xpath('//div[@class="news-list"]/ul/li/div[@class="list-hd"]/h4')
        # 截取前十条，每次爬取只取最新十条
        sites = rowsites[:10]
        for site in sites:
            link = site.xpath('a/@href').extract()[0]
            # 获取到子层URL 递归爬取,内容加入到List中
            yield scrapy.Request(link, callback=self.parse_item)
        # save.savelist(self.items)

    # 爬首页最热 新闻列表
    # def parse(self, response):
    #
    #     sel = Selector(response)
    #     sites = sel.xpath('//ul[@class="list"]/li')
    #     for site in sites:
    #         link = "https://voice.hupu.com" + site.xpath('a/@href').extract()[0]
    #         print(link)
            # 获取到子层URL 递归爬取,内容加入到List中
            # yield scrapy.Request(link, callback=self.parse_item)
        # save.savelist(self.items)

    # 爬各条新闻的内容
    def parse_item(self, response):
        # 获取子页面内容
        sel = Selector(response)
        news = self.news
        # 新闻内容,所有P标签下的文本
        content = sel.xpath('//div[@class="artical-main-content"]').extract()[0]
        news['content'] = content.strip()

        # 新闻URL
        news['url'] = response.url.strip()

        # 根据 URL 来处理 新闻来源 和 新闻类型 参数
        url = news['url']
        source = 100
        sport = 100
        # 来源
        if "hupu" in url:
            source = 1
            # 新闻类型
            if "nba" in url:
                sport = 1
            elif "soccer" in url:
                sport = 2
            elif "f1" in url:
                sport = 3
            elif "china" in url:
                sport = 7
        elif "sina" in url:
            source = 3
            # 新闻类型
            if "nba" in url:
                sport = 1
            elif "/g" in url:
                sport = 2
            elif "tennis" in url:
                sport = 4
            elif "pingpang" in url:
                sport = 5
            elif "volleyball" in url:
                sport = 6
        news['source'] = source
        news['sport'] = sport

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
        # save.savenews(news)
        yield self.news




