from scrapy.spiders import Spider
from scrapy.selector import Selector
from tutroial.items import NewsItem


# 获取新闻链接和标题
class DmozSpider(Spider):
    name = "firstSpider"
    news = NewsItem()
    # 过滤爬取的域名
    allowed_domains = ["sports.sina.com.cn"]

    start_urls = [
        "http://sports.sina.com.cn/china/j/2018-06-15/doc-ihcyszrz7654078.shtml",
        "http://sports.sina.com.cn/china/j/2018-06-15/doc-ihcyszrz7727720.shtml",
        "http://sports.sina.com.cn/china/j/2018-06-16/doc-ihcyszsa3960378.shtml",
        "http://sports.sina.com.cn/china/j/2018-06-16/doc-ihcyszsa3989112.shtml",
        "http://sports.sina.com.cn/china/j/2018-06-16/doc-ihcyszsa4139667.shtml",
        "http://sports.sina.com.cn/china/j/2018-06-16/doc-ihcyszsa4425674.shtml",
        "http://sports.sina.com.cn/china/j/2018-06-16/doc-ihcyszsa5515706.shtml",
        "http://sports.sina.com.cn/china/zjclassic/2018-06-17/doc-ihcyszsa6281931.shtml",
        "http://sports.sina.com.cn/tennis/atp/2018-06-16/doc-ihcyszsa1273789.shtml",
        "http://sports.sina.com.cn/tennis/atp/2018-06-17/doc-iheauxvy7056361.shtml",
        "http://sports.sina.com.cn/tennis/atp/2018-06-16/doc-ihcyszsa1808765.shtml",
        "http://sports.sina.com.cn/tennis/atp/2018-06-14/doc-ihcwpcmq8064913.shtml",
        "http://sports.sina.com.cn/tennis/atp/2018-06-14/doc-ihcwpcmq6794981.shtml",
        "http://sports.sina.com.cn/others/volleyball/2018-06-17/doc-ihcyszsa7399365.shtml",
        "http://sports.sina.com.cn/others/volleyball/2018-06-18/doc-iheauxvy9665304.shtml",
        "http://sports.sina.com.cn/others/volleyball/2018-06-18/doc-iheauxvy8712853.shtml",
        "http://sports.sina.com.cn/others/volleyball/2018-06-18/doc-iheauxvy8680781.shtml",
        "http://sports.sina.com.cn/others/volleyball/2018-06-17/doc-iheauxvy5246598.shtml",
        "http://sports.sina.com.cn/others/volleyball/2018-06-17/doc-iheauxvy4763389.shtml",
        "http://sports.sina.com.cn/others/volleyball/2018-06-17/doc-iheauxvy4616070.shtml",
        "http://sports.sina.com.cn/others/pingpang/2018-06-19/doc-iheauxvz3121157.shtml",
        "http://sports.sina.com.cn/others/pingpang/2018-06-19/doc-iheauxvz2310507.shtml",
        "http://sports.sina.com.cn/others/pingpang/2018-06-18/doc-iheauxvz1277249.shtml",
        "http://sports.sina.com.cn/others/pingpang/2018-06-18/doc-iheauxvy7722209.shtml",
        "http://sports.sina.com.cn/others/pingpang/2018-06-17/doc-iheauxvy6553284.shtml",
        "http://sports.sina.com.cn/others/pingpang/2018-06-17/doc-iheauxvy6545499.shtml",
        "http://sports.sina.com.cn/others/pingpang/2018-06-17/doc-iheauxvy6504341.shtml",
        "http://sports.sina.com.cn/others/pingpang/2018-06-16/doc-ihcyszsa1790743.shtml",
    ]

    def parse(self, response):
        news = self.news

        sel = Selector(response)

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

        # 去除HD
        index_hd1 = con.find('<!-- Hd begin -->')
        con1 = con[0:index_hd1]
        index_hd2 = con.find('<!-- Hd end -->')
        con2 = con[index_hd2:]
        con = con1 + con2

        # 去除首张图
        index_img1 = con.find('<img')
        con1 = con[0:index_img1]
        index_img2 = con.find('.jpg">')
        con2 = con[index_img2 + 6:]
        con = con1 + con2

        news['content'] = con
        news['author'] = ""
        yield news

