//index.js
//获取应用实例
var app = getApp()
var utils = require('../../utils/util.js')
Page({
  data: {
    list: [],
    duration: 2000,
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    loading: false,
    plain: false
  },
  //事件处理函数 绑定了swiper的点击事件
  bindViewTap(e) {
    wx.navigateTo({
      url: '../detail/detail?news_id=' + e.target.dataset['news_id'],
      success: function (res) {
        console.log(res)
      },
      fail: function (res) {
        console.log(res)
      }
    })
  },
  
  onLoad() {
    let that = this
    wx.request({
      url: 'https://laojj.cn/weChat/getNewsList',
      headers: {
        'Content-Type': 'application/json'
      },
      data:{
        openId:app.globalData.keyUserinfo.openid,
        way:'recommend'
      },
      success(res) {
        that.setData({
          banner: res.data,
          list: [{ header: '今日推荐' }].concat(res.data)
        })
      }
    })
    this.index = 1
    //调用应用实例的方法获取全局数据
    // app.getUserInfo(function(userInfo){
    //   //更新数据
    //   that.setData({
    //     userInfo:userInfo
    //   })
    // })
    
  },

  onShow(){
    this.onLoad()
  }
})
