package com.chengtao.wisdomgarden.http

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Response
import retrofit2.Retrofit


/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 4:27 AM
 * Description :
 */
@Suppress("MemberVisibilityCanPrivate")
abstract class HttpRequest<T>() : HttpRequestListener {
  companion object {
    var httpClient = HttpClient()
  }

  var requestId: Short = 0
  var retrofit: Retrofit? = null
  var disposable: Disposable? = null//用于取消请求

  init {
    if (retrofit == null) {
      retrofit = getRetrofitCreator().create()
    }
  }

  fun execute() {
    httpClient.execute(this)
  }

  fun cancel() {
    disposable?.dispose()
  }

  //-----抽象方法,需要实现

  /**
   * 获取Observable对象
   *
   * @return Observable对象
   */
  abstract fun getObservable(): Observable<Response<T>>?

  /**
   * 获取RetrofitCreator对象
   *
   * @return RetrofitCreator对象
   */
  protected abstract fun getRetrofitCreator(): RetrofitCreator
}