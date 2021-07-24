Page({
  data: {
    hint:'',
    list: {},
    loading: false,
    plain: false,
    sport_id:0,
    page: 0,
  },
  onReady: function () {
    wx.setNavigationBarTitle({
      title: this.title
    })
  },
  onLoad: function (options) {
    var that = this
    this.title = options.title
    this.sport_id = options.id,
    this.page = 1
    wx.request({
      url: 'https://laojj.cn/weChat/getNewsList',
      headers: {
        'Content-Type': 'application/json'
      },
      data:{
        way:'category',
        sport_id: options.id,
        page: this.page
      },
      success: function (res) {
        that.setData({
          list: res.data
        })
      }
    })
  },
  
  loadMore:function(e){
    this.page = this.page+1
    var that = this
    that.setData({ loading: true })
    wx.request({
      url: 'https://laojj.cn/weChat/getNewsList',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        way: 'category',
        sport_id: this.sport_id,
        page: this.page
      },
      success: function (res) {
        if (res.data.length>0){
          that.setData({
            list: that.data.list.concat(res.data),
            loading: false,
            hint:''
          })
        }else{
          that.setData({
            loading: false,
            hint: '老铁，没有更多啦！！'
          })
        }
      }
    })
  }

})
