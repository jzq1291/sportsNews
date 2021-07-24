Page({
  data: {
    list: []
  },
  onLoad: function () {
    var that = this
    wx.request({
      url: 'https://laojj.cn/weChat/getTheme',
      headers: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        that.setData({
          list: res.data
        })
      }
    })
  }
})