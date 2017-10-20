package com.chengtao.wisdomgarden.utils

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 8:55 AM
 * Description :
 */
object BaseURLUtils {
  private val BASE_URL_FILE = "BASE_URL_FILE"
  private val DOMAIN_OR_IP = "DOMAIN_OR_IP"
  private val PORT = "PORT"
  fun changeBaseURl(domainOrIp: String, port: String) {
    if (ApplicationUtils.applicationContext != null) {
      SharedPreferencesUtils.putAsynchronous(ApplicationUtils.applicationContext!!, BASE_URL_FILE,
          DOMAIN_OR_IP,
          domainOrIp)
      SharedPreferencesUtils.putAsynchronous(ApplicationUtils.applicationContext!!, BASE_URL_FILE,
          PORT,
          port)
    }
  }

  fun getDomainOrIp(): String {
    return if (ApplicationUtils.applicationContext != null) {
      SharedPreferencesUtils.getString(ApplicationUtils.applicationContext!!, BASE_URL_FILE,
          DOMAIN_OR_IP)
    } else {
      ""
    }
  }

  fun getPort(): String {
    return if (ApplicationUtils.applicationContext != null) {
      SharedPreferencesUtils.getString(ApplicationUtils.applicationContext!!, BASE_URL_FILE, PORT)
    } else {
      ""
    }
  }

  fun getBaseUrl(): String {
    return if (ApplicationUtils.applicationContext != null) {
      val domainOrIp = getDomainOrIp()
      val port = getPort()
      if (StringUtils.isStringNull(domainOrIp, port)) {
        ""
      } else {
        "http://$domainOrIp:$port"
      }
    } else {
      ""
    }
  }
}