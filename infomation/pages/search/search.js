const app = getApp()
Page({
  data: {
    list: {},
  },
  onReady: function () {
    wx.setNavigationBarTitle({
      title: '新闻检索'
    })
  },
  onLoad: function (options) {
    
  },

  formSubmit:function(e){
    var that = this
    wx.request({
      url: 'https://laojj.cn/weChat/getNewsList',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        way: 'search',
        title: e.detail.value.title,
      },
      success:function(res){
        that.setData({
          list:res.data
        })
      }
    })
  }

})
