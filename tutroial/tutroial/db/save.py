#!/usr/bin/python3
import pymysql


# 保存一条新闻
def savenews(news):

    # 打开数据库连接
    db = pymysql.connect("localhost", "root", "", "sports_news")

    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    sql = "insert into news (sport_id, source_id, title, url, img_url, author, date, content) " \
          "values(%d, %d, '%s', '%s', '%s', '%s', '%s', '%s')" \
          % (news['sport'], news['source'], news['title'], news['url'], news['img_url'],
             news['author'], news['date'], news['content'])

    try:
        # 使用 execute()  方法执行 SQL 查询
        cursor.execute(sql)
        # 提交
        db.commit()
    except Exception as e:
        print(e)
        # 出错回滚
        db.rollback()

    # 关闭数据库连接
    cursor.close()
    db.close()


# 批量保存
def savelist(items):
    print(items)


# 调试
def paramTest(news):
    print(news['title'])
    print(news['img_url'])
    print(news['date'])
    print(news['author'])
    print(news['url'])

    url = news['url']
    if "hupu" in url:
        print('hupu')
        if "nba" in url:
            print("NBA")
    elif "sina" in url:
        print("新浪")
        if "nba" in url:
            print("NBA")
