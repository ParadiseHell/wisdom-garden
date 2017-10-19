package com.chengtao.wisdomgarden.request

import com.chengtao.wisdomgarden.api.ServiceAPI
import com.chengtao.wisdomgarden.http.HttpResponseListener
import com.chengtao.wisdomgarden.entity.Service
import com.chengtao.wisdomgarden.utils.StringUtils
import io.reactivex.Observable
import retrofit2.Response

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 11:39 AM
 * Description :
 */
class GetServicesByNameRequest(
    httpResponseListener: HttpResponseListener) : BaseRequest<ArrayList<Service>>(
    httpResponseListener) {
  var name: String? = null
  override fun getObservable(): Observable<Response<ArrayList<Service>>>? {
    return if (StringUtils.isStringNull(name)) {
      null
    } else {
      retrofit?.create(ServiceAPI::class.java)?.getServicesByName(name!!)
    }
  }

}