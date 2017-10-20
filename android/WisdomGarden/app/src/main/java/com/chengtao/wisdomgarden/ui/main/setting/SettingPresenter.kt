package com.chengtao.wisdomgarden.ui.main.setting

import android.content.Context
import com.chengtao.wisdomgarden.EventBusMessageID
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.api.WisdomGardenRetrofitCreator
import com.chengtao.wisdomgarden.entity.EventBusMessage
import com.chengtao.wisdomgarden.utils.BaseURLUtils
import com.chengtao.wisdomgarden.utils.StringUtils
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
    if (!StringUtils.isStringNull(domainOrIp, port)) {
      WisdomGardenRetrofitCreator.baseUrl = "http:/$domainOrIp:$port"
      EventBus.getDefault().post(EventBusMessage(EventBusMessageID.CHANGE_BASE_URL, true))
      finish()
    } else {
      mView?.toast("域名或ip或端口不能为空")
    }
  }

  override fun onData(requestId: Short, response: Any?) {
  }

}