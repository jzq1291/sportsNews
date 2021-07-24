# from scrapy import cmdline
# cmdline.execute("scrapy crawl firstSpider".split())
import time
import os
while True:
    os.system("scrapy crawl hupu")
    os.system("scrapy crawl sina_nba")
    os.system("scrapy crawl sina_global")
    # 半小时执行一次  30*60
    time.sleep(1800)
