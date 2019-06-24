// pages/welcome/index.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: 
  {
    name: '',
    vedio: '',
    flag : 0
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var keyword = options.keyword;
    this.setData({
      name: keyword
    })
    this.getdata(keyword);
  },

  //获取景点详情
  getdata: function (view) {
    var that = this;
    console.log(view);
    wx.request({
      url: 'http://localhost:8080/yuexing/VideoServlet',
      data: {
        viewName: view,
      },
      header: {
        "Content-Type": "applciation/json"
      },
      success: function (res) {
        if(res.data[0] == null){
          flag : 1 
        } else{
          that.setData({
            item: res.data
          })
        }
      }
    })
  },

  gotoNewMusice: function () {
    wx.navigateTo({
      url: '../newMusic/index',
    })
  }
})