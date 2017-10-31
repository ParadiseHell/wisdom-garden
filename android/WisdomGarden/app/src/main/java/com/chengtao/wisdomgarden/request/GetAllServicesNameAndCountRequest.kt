package com.chengtao.wisdomgarden.request

import com.chengtao.wisdomgarden.api.ServiceAPI
import com.chengtao.wisdomgarden.http.HttpResponseListener
import com.chengtao.wisdomgarden.entity.ServiceNameAndCount
import io.reactivex.Observable
import retrofit2.Response

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 11:39 AM
 * Description :
 */
class GetAllServicesNameAndCountRequest(
    httpResponseListener: HttpResponseListener) : BaseRequest<ArrayList<ServiceNameAndCount>>(
    httpResponseListener) {
  override fun getObservable(): Observable<Response<ArrayList<ServiceNameAndCount>>>? =
      retrofitMap[getRetrofitName()]?.create(ServiceAPI::class.java)?.getServiceNameAncCount()

}