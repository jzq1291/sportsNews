from scrapy.spiders import Spider
from scrapy.selector import Selector


# 获取单条新闻
class DmozSpider(Spider):
    name = "audio"
    # 过滤爬取的域名
    allowed_domains = ["xiami.com"]

    start_urls = [
        "http://h.xiami.com/one-share.html?id=1802756798"
    ]

    def parse(self, response):

        sel = Selector(response)

        # img_url = sel.xpath('//div[@class="artical-importantPic"]/img/@src').extract()[0]

        url = sel.xpath('//div[@id="info"]/audio').extract()

        print(url)



