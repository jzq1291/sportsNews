//app.js
App({

  globalData: {
    userInfo: null,
    keyUserinfo: null
  },

  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    var that = this

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 登录后转到后台
              wx.request({
                url: 'https://laojj.cn/weChat/userServlet',
                headers: {
                  'Content-Type': 'application/json'
                },
                data: {
                  way: 'login',
                  userInfo: this.globalData.userInfo,
                  keyUserinfo: this.globalData.keyUserinfo
                },
                success: res => {
                 
                }
              })
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          console.log(res.code)
          //发起网络请求
          wx.request({
            // 获取openID
            url: 'https://api.weixin.qq.com/sns/jscode2session',
            data: {
              appid: 'wx890a5a4d5b3c142e',
              secret:'9b90d7b288668af05f3028e588612291',
              js_code: res.code,
            },
            success: res => {
              this.globalData.keyUserinfo = res.data
              console.log(res.data)
            }
          })
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    })
  }
})