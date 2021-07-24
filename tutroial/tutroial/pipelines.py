# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
from scrapy.exceptions import DropItem
import pymysql


class TutroialPipeline(object):
    def __init__(self):
        self.ids_seen = set()
        self.main = "localhost"
        self.user = "root"
        self.password = ""
        self.database = "sports_news"
        # 打开数据库连接
        self.db = pymysql.connect(self.main, self.user, self.password, self.database)
        # 使用 cursor() 方法创建一个游标对象 cursor
        self.cursor = self.db.cursor()
        # 删除redis里面的key
        sql = "select url from news"
        try:
            self.cursor.execute(sql)
            results = self.cursor.fetchall()
            print(results.__len__())
            for row in results:
                self.ids_seen.add(row[0])
        except Exception as e:
            print(e)

    def process_item(self, news, spider):
        if news['url'] in self.ids_seen:
            raise DropItem("Duplicate item found: %s" % news['url'])
        else:
            values = [news['sport'], news['source'], news['title'], news['url'], news['img_url'],
                      news['author'], news['date'], news['content']]
            try:
                # 使用 execute()  方法执行 SQL 查询
                self.do_insert(values)
                # 提交
                self.db.commit()
                self.ids_seen.add(news['url'])
                return news
            except Exception as e:
                print(e)
                # 出错回滚
                self.db.rollback()

    def do_insert(self, values):
        sql = "insert into news (sport_id, source_id, title, url, img_url, author, date, content) " \
              "values(%s, %s, %s, %s, %s, %s, %s, %s)"
        self.cursor.execute(sql, values)

    def close_spider(self, spider):
        self.db.close()
        self.cursor.close()


