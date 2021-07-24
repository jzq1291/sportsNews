// pages/message/message.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: '留言板',
    msg: {},
    content: '',
    email:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    wx.request({
      url: 'https://laojj.cn/weChat/messageServlet',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        openid: app.globalData.keyUserinfo.openid,
        way: 'getmessage'
      },
      success: function (res) {
        that.setData({
          msg:res.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.setNavigationBarTitle({
      title: this.data.title
    })
  },
  changeInputValue(ev) {
    this.setData({
      inputVal: ev.detail.value
    })
  },

  formSubmit:function(e){
    var that = this
    wx.request({
      url: 'https://laojj.cn/weChat/messageServlet',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        openid: app.globalData.keyUserinfo.openid,
        way: 'addmessage',
        content: e.detail.value.content,
        email:e.detail.value.email
      },
      success: function () {
        // 回到上一页
        that.onLoad()
        that.setData({
          content:'',
          email:''
        })
      }
    })
  }
})