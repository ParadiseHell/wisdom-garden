package com.chengtao.wisdomgarden.http

import android.text.TextUtils
import android.util.Log
import android.webkit.URLUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 6:25 AM
 * Description :
 */
@Suppress("MemberVisibilityCanPrivate")
abstract class RetrofitCreator protected constructor() {

  private fun getRetrofit(): Retrofit {
    Log.e("TAG", "getRetrofit")
    val builder = Retrofit.Builder()
    if (getBaseUrl() == "") {
      builder.baseUrl("http://39.106.63.196:8080/")
    } else if (!(URLUtil.isHttpUrl(getBaseUrl()) || URLUtil.isHttpsUrl(
        getBaseUrl()))) {
      builder.baseUrl("http://39.106.63.196:8080/")
    } else {
      builder.baseUrl(getBaseUrl())
    }
    return builder.client(getOkHttpClient().newBuilder().addInterceptor(
        HttpLoggingInterceptor(HttpLogger()).setLevel(BODY)).build())
        .addConverterFactory(getConverterFactory())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
  }

  fun create(): Retrofit {
    return getRetrofit()
  }

  /**
   * 获取 BaseUrl
   *
   * @return BaseUrl
   */
  protected abstract fun getBaseUrl(): String

  /**
   * 获取 okHttpClient
   *
   * @return okHttpClient
   */
  protected abstract fun getOkHttpClient(): OkHttpClient

  /**
   * 获取 converter factory
   *
   * @return converter factory
   */
  protected abstract fun getConverterFactory(): Converter.Factory
}

private class HttpLogger : HttpLoggingInterceptor.Logger {
  override fun log(message: String) {
    if (!TextUtils.isEmpty(message)) {
      Log.e("HttpLog", " " + message)
    }
  }
}