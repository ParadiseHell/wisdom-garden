package com.chengtao.wisdomgardern.request

import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgardern.api.UserAPI
import com.chengtao.wisdomgardern.http.HttpResponseListener
import com.chengtao.wisdomgardern.response.User
import io.reactivex.Observable
import retrofit2.Response

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 11:03 AM
 * Description :
 */
class LoginRequest(httpResponseListener: HttpResponseListener) : BaseRequest<User>(
    httpResponseListener) {
  var userName: String? = null
  var password: String? = null
  override fun getObservable(): Observable<Response<User>>? {
    if (!StringUtils.isStringNull(userName, password)) {
      return retrofit?.create(UserAPI::class.java)?.login(userName!!, password!!)
    }
    return null
  }
}