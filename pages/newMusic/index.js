// pages/newMusic/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    this.recorderManager = wx.getRecorderManager();
    this.recorderManager.onError(function(){
      that.tip("录音失败！")
    });
    this.recorderManager.onStop(function(res){
      that.setData({
        src: res.tempFilePath 
      })
      console.log(res.tempFilePath )
      that.tip("录音完成！")
    });

    this.innerAudioContext = wx.createInnerAudioContext();
    this.innerAudioContext.onError((res) => {
      that.tip("播放录音失败！")
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  // onLoad: function () {
  //   console.log('onLoad')
  //   var that = this
  //   wx.request({
  //    url: 'https://www.123.com/weixin/getdownlist', //真实的接口地址
  //     data: {},
  //     header: {
  //       'content-type': 'application/json'
  //     },
  //     success: function (res) {
  //       console.log(res.data)
  //       that.setData({
  //         Industry: res.data //设置数据
  //       })
  //     },
  //     fail: function (err) {
  //       console.log(err)
  //     }
  //   })
  // },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
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
  
  }

  /**
  * 提示
  */
  , tip: function (msg) {
    wx.showModal({
      title: '提示',
      content: msg,
      showCancel: false
    })
  }

  /**
   * 录制aac音频
   */
  ,startRecordAac: function(){
    this.recorderManager.start({
      format: 'aac'
    });
  }

  /**
   * 录制mp3音频
  */
  , startRecordMp3: function () {
    this.recorderManager.start({
      format: 'mp3'
    });
  }

  /**
   * 停止录音
   */
  ,stopRecord: function(){
    this.recorderManager.stop()
  }

  /**
   * 播放录音
   */
  , playRecord: function(){
    var that = this;
    var src = this.data.src;
    if (src == '') {
      this.tip("请先录音！")
      return;
    }
    this.innerAudioContext.src = this.data.src;
    this.innerAudioContext.play()
  },
  // uploadRecord: function () {
  //   wx.uploadFile({
  //     url: "https://xxx.com/fileUpload",//演示域名、自行配置
  //     filePath: this.tempFilePath,
  //     name: 'file',
  //     header: {
  //       "Content-Type": "multipart/form-data"
  //     },
  //     formData:
  //     {
  //       userId: 12345678 //附加信息为用户ID
  //     },
  //     success: function (res) {
  //       console.log(res);
  //       wx.showToast({
  //         title: '上传成功',
  //         icon: 'success',
  //         duration: 2000
  //       })
  //     },
  //     fail: function (res) {
  //       console.log(res);
  //     },
  //     complete: function (res) {

  //     }
  //   })
  // }

})