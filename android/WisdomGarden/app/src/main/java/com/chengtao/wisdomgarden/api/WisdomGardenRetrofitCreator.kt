package com.chengtao.wisdomgarden.api

import android.text.TextUtils
import android.util.Log
import com.chengtao.wisdomgarden.http.RetrofitCreator
import com.chengtao.wisdomgarden.utils.BaseURLUtils
import com.chengtao.wisdomgarden.utils.UserUtils
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Converter.Factory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 11:22 AM
 * Description :
 */
class WisdomGardenRetrofitCreator private constructor() : RetrofitCreator() {
  companion object {
    var instance = WisdomGardenRetrofitCreator()
    var baseUrl: String? = null
  }

  override fun getBaseUrl(): String = if (TextUtils.isEmpty(baseUrl)) {
    BaseURLUtils.getBaseUrl()
  } else {
    baseUrl!!
  }

  override fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(
            WisdomGardenInterceptor())
        .build()
  }

  private class WisdomGardenInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
      //获取旧的请求
      val oldRequest = chain.request()
      val builder = oldRequest.newBuilder()
      val userName = if (TextUtils.isEmpty(UserUtils.getCurrentUserName())) {
        ""
      } else {
        UserUtils.getCurrentUserName()
      }
      builder.addHeader("userName", userName!!)
      val password = if (TextUtils.isEmpty(UserUtils.getCurrentUserPassword())) {
        ""
      } else {
        UserUtils.getCurrentUserPassword()
      }
      builder.addHeader("password", password!!)
      return chain.proceed(builder.build())
    }
  }

  override fun getConverterFactory(): Factory {
    val builder = GsonBuilder().serializeNulls()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    return GsonConverterFactory.create(builder.create())
  }
}