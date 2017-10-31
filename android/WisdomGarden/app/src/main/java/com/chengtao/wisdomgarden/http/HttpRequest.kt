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
@Suppress("MemberVisibilityCanPrivate", "LeakingThis")
abstract class HttpRequest<T> : HttpRequestListener {
  companion object {
    var httpClient = HttpClient()
    val retrofitMap: HashMap<String, Retrofit> = HashMap()
  }

  var requestId: Short = 0
  var disposable: Disposable? = null//用于取消请求

  init {
    if (!retrofitMap.containsKey(getRetrofitName())) {
      retrofitMap.put(getRetrofitName(), getRetrofitCreator().create())
    }
  }

  fun execute() {
    httpClient.execute(this)
  }

  fun cancel() {
    disposable?.dispose()
  }

  /**
   * 检查retrofit,如果为空则初始化
   */
  fun checkRetrofitNotNull() {
    synchronized(retrofitMap) {
      if (retrofitMap[getRetrofitName()] == null) {
        retrofitMap.put(getRetrofitName(), getRetrofitCreator().create())
      }
    }
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

  protected fun getRetrofitName(): String {
    return "com.chengtao.wisdomgarden.http.HttpRequest"
  }
}