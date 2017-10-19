package com.chengtao.wisdomgardern.http

import retrofit2.Response

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 7:00 AM
 * Description :
 */
interface HttpRequestListener {
  /**
   * 当请求开始
   *
   * @param requestId 请求ID
   */
  fun onStart(requestId: Short)

  /**
   * 当获取数据
   *
   * @param requestId 请求ID
   * @param response 响应实体
   */
  fun <T> onData(requestId: Short, response: Response<T>?)

  /**
   * 当处理完请求响应数据后
   *
   * @param requestId 请求ID
   */
  fun onComplete(requestId: Short)

  /**
   * 当请求出现错误
   *
   * @param requestId 请求ID
   * @param e 异常
   */
  fun onError(requestId: Short, e: Throwable?)
}