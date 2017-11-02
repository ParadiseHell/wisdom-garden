package com.chengtao.wisdomgarden.request

import android.util.Log
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.api.ErrorString
import com.chengtao.wisdomgarden.api.ErrorType
import com.chengtao.wisdomgarden.api.WisdomGardenRetrofitCreator
import com.chengtao.wisdomgarden.entity.Error
import com.chengtao.wisdomgarden.http.HttpClient
import com.chengtao.wisdomgarden.http.HttpRequest
import com.chengtao.wisdomgarden.http.HttpResponseListener
import com.chengtao.wisdomgarden.http.RetrofitCreator
import com.chengtao.wisdomgarden.utils.UserUtils
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
abstract class BaseRequest<T>() : HttpRequest<T>() {
  protected var httpResponseListener: HttpResponseListener? = null

  protected constructor(httpResponseListener: HttpResponseListener) : this() {
    this.httpResponseListener = httpResponseListener
  }

  companion object {
    val mGson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()!!
  }

  override fun getRetrofitCreator(): RetrofitCreator = WisdomGardenRetrofitCreator.instance

  override fun onStart(requestId: Short) {
    httpResponseListener?.onStart(requestId)
  }

  override fun <T> onData(requestId: Short, response: Response<T>?) {
    if (response == null) {
      httpResponseListener?.onData(requestId, null)
    } else {
      val errorBody = response.errorBody()
      if (errorBody != null) {
        onError(requestId, handleError(errorBody))
      } else {
        httpResponseListener?.onData(requestId, response.body())
      }
    }
  }

  override fun onComplete(requestId: Short) {
    httpResponseListener?.onComplete(requestId)
  }

  override fun onError(requestId: Short, e: Throwable?) {
    if (e != null) {
      if (e.message == HttpClient.REQUEST_NETWORK_ERROR) {
        httpResponseListener?.onError(requestId, R.string.no_internet)
      } else {
        onError(requestId, handleError(e))
      }
    }
  }

  protected fun onError(requestId: Short, error: Error) {
    when (error.error) {
      ErrorString.MISSING_PARAMETER -> {
        httpResponseListener?.onError(requestId, R.string.missing_parameter)
      }
      ErrorString.INTERNAL_SERVER_ERROR -> {
        httpResponseListener?.onError(requestId, R.string.internal_server_error)
      }
      ErrorString.UNAUTHORIZED -> {
        //清楚用户相关信息
        UserUtils.clean()
        httpResponseListener?.onError(requestId, R.string.unauthorized)
        httpResponseListener?.onSpecialError(requestId, ErrorType.UNAUTHORIZED)
      }
      ErrorString.USER_EXIST -> {
        httpResponseListener?.onError(requestId, R.string.user_exist)
        httpResponseListener?.onSpecialError(requestId, ErrorType.USER_EXIST)
      }
      ErrorString.USER_NAME_OR_PASSWORD_WRONG -> {
        httpResponseListener?.onError(requestId, R.string.user_name_or_password_wrong)
        httpResponseListener?.onSpecialError(requestId, ErrorType.USER_NOT_EXIST)
      }
      else -> {
        httpResponseListener?.onError(requestId, R.string.unknown_error)
      }
    }
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
        error = mGson.fromJson(errorBody.string(), Error::class.java)
      } catch (e: Exception) {
        //do nothing
        Log.e("TAG", "e${e.message}")
      }
    }
    if (error == null) {
      error = Error()
      error.error = ""
    }
    return error
  }
}