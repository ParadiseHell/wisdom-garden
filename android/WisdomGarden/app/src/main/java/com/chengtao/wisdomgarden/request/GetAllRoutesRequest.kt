package com.chengtao.wisdomgarden.request

import com.chengtao.wisdomgarden.api.RouteAPI
import com.chengtao.wisdomgarden.http.HttpResponseListener
import com.chengtao.wisdomgarden.entity.Route
import io.reactivex.Observable
import retrofit2.Response

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 11:39 AM
 * Description :
 */
class GetAllRoutesRequest(
    httpResponseListener: HttpResponseListener) : BaseRequest<ArrayList<Route>>(
    httpResponseListener) {
  override fun getObservable(): Observable<Response<ArrayList<Route>>>? =
      retrofitMap[getRetrofitName()]?.create(RouteAPI::class.java)?.getAllRoute()
}