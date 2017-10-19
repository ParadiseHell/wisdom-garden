package com.chengtao.wisdomgardern.api

import com.chengtao.wisdomgardern.response.UserResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 7:23 AM
 * Description :
 */
interface API {
  interface User {
    @POST("/api/user/login")
    @FormUrlEncoded
    fun login(@Field("user_name") userName: String, @Field(
        "password") password: String): Observable<Response<UserResponse>>

    @POST("/api/user/register")
    @FormUrlEncoded
    fun register(@Field("user_name") userName: String, @Field(
        "password") password: String): Observable<Response<UserResponse>>
  }
}