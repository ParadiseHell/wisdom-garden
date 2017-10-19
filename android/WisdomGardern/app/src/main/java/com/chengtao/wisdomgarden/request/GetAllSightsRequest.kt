package com.chengtao.wisdomgarden.request

import com.chengtao.wisdomgarden.api.SightAPI
import com.chengtao.wisdomgarden.http.HttpResponseListener
import com.chengtao.wisdomgarden.response.Sight
import io.reactivex.Observable
import retrofit2.Response

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 11:39 AM
 * Description :
 */
class GetAllSightsRequest(
    httpResponseListener: HttpResponseListener) : BaseRequest<ArrayList<Sight>>(
    httpResponseListener) {
  override fun getObservable(): Observable<Response<ArrayList<Sight>>>? =
      retrofit?.create(SightAPI::class.java)?.getAllSights()

}