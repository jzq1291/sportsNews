// pages/profile/profile.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    checkboxItems: [

    ],
    hint:'',
    hidden: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    wx.request({
      url: 'https://laojj.cn/weChat/profileServlet',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        openid: app.globalData.keyUserinfo.openid,
        way:'getProfile'
      },
      success: function (res) {
        that.setData({
          checkboxItems: res.data
        })
      }
    })
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    wx.setNavigationBarTitle({
      title: '用户喜好'
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  checkboxChange: function (e) {
    var checked = e.detail.value
    // 爱好不能超过三个
    if ((checked.length)<4){
      var changed = {}
      for (var i = 0; i < this.data.checkboxItems.length; i++) {
        // int 转 String
        var sportId = this.data.checkboxItems[i].sportId + ''
        if (checked.indexOf(sportId) !== -1) {
          changed['checkboxItems[' + i + '].checked'] = true
        } else {
          changed['checkboxItems[' + i + '].checked'] = false
        }
      }
      this.setData(changed)
      this.setData({
        hint: ''
      })
    }else{
      this.setData({
        checkboxItems:this.data.checkboxItems,
        hint:'喜好请不要超过三个'
      })
    }
  },

// 表单提交
  formSubmit: function (e) {
    var value = e.detail.value.checkbox
    if (value.length>0){
      var profile = e.detail.value.checkbox[0]
      for (var i = 1; i < value.length; i++) {
        profile += ","
        profile += value[i]
      }
    }else{
      profile = ""
    }
    wx.request({
      url: 'https://laojj.cn/weChat/profileServlet',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        openid: app.globalData.keyUserinfo.openid,
        way: 'updateProfile',
        profiles: profile
      },
      success: function () {
        // 回到上一页
        wx.navigateBack({
          
        })
      }
    })
  }

})