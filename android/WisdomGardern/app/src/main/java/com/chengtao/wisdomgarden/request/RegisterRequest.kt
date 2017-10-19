package com.chengtao.wisdomgarden.request

import com.chengtao.wisdomgarden.api.UserAPI
import com.chengtao.wisdomgarden.http.HttpResponseListener
import com.chengtao.wisdomgarden.response.User
import com.chengtao.wisdomgarden.utils.StringUtils
import io.reactivex.Observable
import retrofit2.Response

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 11:03 AM
 * Description :
 */
class RegisterRequest(httpResponseListener: HttpResponseListener) : BaseRequest<User>(
    httpResponseListener) {
  var userName: String? = null
  var password: String? = null
  override fun getObservable(): Observable<Response<User>>? {
    if (!StringUtils.isStringNull(userName, password)) {
      return retrofit?.create(UserAPI::class.java)?.register(userName!!, password!!)
    }
    return null
  }
}