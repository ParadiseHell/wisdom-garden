package com.chengtao.wisdomgardern.request

import com.chengtao.wisdomgardern.http.HttpRequest
import com.chengtao.wisdomgardern.http.HttpResponseListener
import com.chengtao.wisdomgardern.response.Error
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response


/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 7:31 AM
 * Description :
 */
@Suppress("MemberVisibilityCanPrivate")
abstract class BaseRequest<T>(
    protected var httpResponseListener: HttpResponseListener) : HttpRequest<T>() {
  companion object {
    val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
  }

  override fun onStart(requestId: Short) {
    httpResponseListener.onStart(requestId)
  }

  override fun <T> onData(requestId: Short, response: Response<T>?) {
    if (response == null) {
      httpResponseListener.onData(requestId, null)
    } else {
      val errorBody = response.errorBody()
      if (errorBody != null) {

      } else {
        httpResponseListener.onData(requestId, response.body())
      }
    }
  }

  override fun onComplete(requestId: Short) {
    httpResponseListener.onComplete(requestId)
  }

  override fun onError(requestId: Short, e: Throwable?) {

  }

  fun handleError(errorObject: Any): Error {
    var error: Error? = null
    var errorBody: ResponseBody? = null
    try {
      when (errorObject) {
        is HttpException -> {
          errorBody = errorObject.response().errorBody()
        }
        is ResponseBody -> {
          errorBody = errorObject
        }
      }
    } catch (e: Exception) {
      //do nothing
    }
    if (errorBody != null) {
      try {
        error = gson.fromJson(errorBody.string(), Error::class.java)
      } catch (e: Exception) {
        //do nothing
      }
    }
    if (error == null) {
      error = Error()
      error.error = ""
    }
    return error
  }
}