package com.chengtao.wisdomgardern.http

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 7:00 AM
 * Description :
 */
interface HttpResponseListener {
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
  fun onData(requestId: Short, response: Any?)

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
   * @param stringId 错误提示字符串资源id
   */
  fun onError(requestId: Short, stringId: Int)

  /**
   * 当请求为特殊错误时发生
   *
   * @param requestId 请求ID
   * @param errorType 错误类型
   */
  fun onSpecialError(requestId: Short, errorType: Short)
}