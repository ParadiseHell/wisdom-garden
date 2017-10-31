package com.chengtao.wisdomgarden.http

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 7:00 AM
 * Description :
 */
class HttpClient {
  companion object {
    val REQUEST_NETWORK_ERROR = "REQUEST_NETWORK_ERROR"
  }

  fun <T> execute(request: HttpRequest<T>) {
    //检查retrofit
    request.checkRetrofitNotNull()
    if (request.getObservable() == null) {
      Log.e("TAG", "executeRequest(CoreSateClient.java:" + Thread.currentThread()
          .stackTrace[2].lineNumber + ")" + "request is not be executed")
      Flowable
          .just(1)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(
              {
                request.onError(request.requestId,
                    IllegalArgumentException("request is not be executed"))
              },
              { e -> request.onError(request.requestId, e) })
      return
    }
    request.getObservable()!!.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { request.onStart(request.requestId) }
        .subscribe(object : Observer<Response<T>> {
          override fun onNext(value: Response<T>?) {
            request.onData(request.requestId, value)
          }

          override fun onError(e: Throwable?) {
            if (isNetWorkError(e)) {//网络请求出错
              request.onError(request.requestId,
                  Exception(REQUEST_NETWORK_ERROR));
            } else {//其他错误
              request.onError(request.requestId, e);
            }
          }

          override fun onComplete() {
            request.onComplete(request.requestId)
          }

          override fun onSubscribe(d: Disposable?) {
            request.disposable = d
          }
        })
  }

  /**
   * 是否为网络错误
   *
   * @param e 　异常
   * @return 是　: true
   */
  private fun isNetWorkError(e: Throwable?): Boolean = when (e) {
    is UnknownHostException -> true
    is ConnectException -> true
    is SocketException -> true
    else -> false
  }
}