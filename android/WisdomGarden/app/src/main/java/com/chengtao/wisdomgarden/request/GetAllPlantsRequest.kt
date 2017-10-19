package com.chengtao.wisdomgarden.request

import com.chengtao.wisdomgarden.api.PlantsAPI
import com.chengtao.wisdomgarden.http.HttpResponseListener
import com.chengtao.wisdomgarden.entity.Plants
import io.reactivex.Observable
import retrofit2.Response

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 11:39 AM
 * Description :
 */
class GetAllPlantsRequest(
    httpResponseListener: HttpResponseListener) : BaseRequest<ArrayList<Plants>>(
    httpResponseListener) {
  override fun getObservable(): Observable<Response<ArrayList<Plants>>>? =
      retrofit?.create(PlantsAPI::class.java)?.getAllPlants()

}