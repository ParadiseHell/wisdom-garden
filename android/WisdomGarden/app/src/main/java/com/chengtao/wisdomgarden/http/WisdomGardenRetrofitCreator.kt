package com.chengtao.wisdomgarden.http

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
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

  override fun getBaseUrl(): String = baseUrl ?: "http://localhost/"

  override fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS).build()
  }

  override fun getConverterFactory(): Factory {
    val builder = GsonBuilder().serializeNulls()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES)
    return GsonConverterFactory.create(builder.create())
  }

}