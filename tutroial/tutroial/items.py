# Item 对象是Python 自定义字典

import scrapy
from scrapy.item import Item, Field


class TutroialItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass


class DmozItem(Item):
    title = Field()
    link = Field()
    desc = Field()


class NewsItem(scrapy.Item):
    title = Field()
    link = Field()
    content = Field()
    img_url = Field()
    date = Field()
    url = Field()
    author = Field()
    source = Field()
    sport = Field()
    pass


