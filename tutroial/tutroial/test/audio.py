import urllib.request

url = "http://h.xiami.com/one-share.html?id=1802756798"
html = urllib.request.urlopen(url)

print(html.read())
