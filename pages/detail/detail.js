// pages/detail/detail.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: '',
    details: '',
    image: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var keyword = options.keyword;
    console.log(keyword);
    this.setData({
      name: keyword
    })
    this.getdata(keyword);
  },

  //获取景点详情
  getdata:function(view){
    var that = this;
    wx.request({
      url: 'http://localhost:8080/yuexing/ViewServlet',
      data:{
        viewName: view,
      },
      header:{
        "Content-Type": "applciation/json"
      },
      success:function(res){;
        that.setData({
          image: res.data.image,
          details: res.data.text
        })
      }
    })
  },

  voice: function () {
    wx.navigateTo({
      url: '../index/index?keyword=' + this.data.name, 
      success: function (res) { },
      fail: function (res) { },
      complete: function (res) { },
    })
  }

})