package com.chengtao.wisdomgarden.ui.main.setting

import android.content.Context
import android.util.Log
import android.webkit.URLUtil
import com.chengtao.wisdomgarden.EventBusMessageID
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.api.WisdomGardenRetrofitCreator
import com.chengtao.wisdomgarden.entity.EventBusMessage
import com.chengtao.wisdomgarden.http.HttpRequest
import com.chengtao.wisdomgarden.utils.BaseURLUtils
import org.greenrobot.eventbus.EventBus

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 8:48 AM
 * Description :
 */
class SettingPresenter(view: SettingContract.View,
    context: Context) : WisdomGardenPresenter<SettingContract.View>(view,
    context), SettingContract.Presenter {
  override fun init() {
    mView?.setDomainOrIp(BaseURLUtils.getDomainOrIp())
    mView?.setPort(BaseURLUtils.getPort())
  }

  override fun changeBaseUrl(domainOrIp: String, port: String) {
    when {
      domainOrIp == "" -> {
        mView?.toast("域名或ip不能为空")
      }
      port == "" -> {
        mView?.toast("端口不能为空")
      }
      else -> {
        val realDomainOrIp = "http://$domainOrIp"
        Log.e("TAG", realDomainOrIp)
        if (!(URLUtil.isHttpUrl(realDomainOrIp) || URLUtil.isHttpsUrl(realDomainOrIp))) {
          mView?.toast("域名或ip有问题")
        } else if (port.toInt() !in 0..65535) {
          mView?.toast("端口必须在0到65535之间")
        } else {
          WisdomGardenRetrofitCreator.baseUrl = "http://$domainOrIp:$port/"
          //清除之前的retrofit缓存
          WisdomGardenRetrofitCreator.instance.retrofitMap.clear()
          HttpRequest.retrofit = null
          //保存ip和端口
          BaseURLUtils.changeBaseURl(domainOrIp, port)
          //通知界面
          EventBus.getDefault().post(EventBusMessage(EventBusMessageID.CHANGE_BASE_URL, true))
          finish()
        }
      }
    }
  }

  override fun onData(requestId: Short, response: Any?) {
  }

}