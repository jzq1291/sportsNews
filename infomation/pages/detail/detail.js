const app = getApp()

Page({
  data: {
    art: {},
  },
  onReady () {
    wx.setNavigationBarTitle({
      title: '详情页面'
    })
  },
  onLoad (options) {
    var that = this
    wx.request({
      url: 'https://laojj.cn/weChat/getNews?',
      headers: {
        'Content-Type': 'application/json'
      },
      data:{
        news_id: options['news_id'],
        openid: app.globalData.keyUserinfo.openid
      },
      success (res) {
        that.setData({
          art: res.data
        })
      }
    })
  }
})