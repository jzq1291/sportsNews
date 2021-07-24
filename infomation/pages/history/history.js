const app = getApp()
Page({
  data: {
    list: {},
  },
  onReady: function () {
    wx.setNavigationBarTitle({
      title: '浏览历史'
    })
  },
  onLoad: function (options) {
    var that = this
    this.title = options.title
    wx.request({
      url: 'https://laojj.cn/weChat/getNewsList',
      headers: {
        'Content-Type': 'application/json'
      },
      data:{
        way:'history',
        openId: app.globalData.keyUserinfo.openid,
      },
      success: function (res) {
        that.setData({
          list: res.data
        })
      }
    })
  },
})
