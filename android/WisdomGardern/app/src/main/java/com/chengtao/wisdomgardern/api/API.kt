package com.chengtao.wisdomgardern.api

import com.chengtao.wisdomgardern.response.Plants
import com.chengtao.wisdomgardern.response.Route
import com.chengtao.wisdomgardern.response.ServiceNameAndCount
import com.chengtao.wisdomgardern.response.Sight
import com.chengtao.wisdomgardern.response.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 7:23 AM
 * Description :
 */
interface API {
  interface UserAPI {
    @POST("/api/user/login")
    @FormUrlEncoded
    fun login(@Field("user_name") userName: String, @Field(
        "password") password: String): Observable<Response<User>>

    @POST("/api/user/register")
    @FormUrlEncoded
    fun register(@Field("user_name") userName: String, @Field(
        "password") password: String): Observable<Response<User>>
  }

  interface SightAPI {
    @GET("/api/sights")
    fun getAllSights(): Observable<Response<ArrayList<Sight>>>
  }

  interface PlantsAPI {
    @GET("/api/plants")
    fun getAllPlants(): Observable<Response<ArrayList<Plants>>>
  }

  interface RouteAPI {
    @GET("/api/routes")
    fun getAllRoute(): Observable<Response<ArrayList<Route>>>
  }

  interface ServiceAPI {
    @GET("/api/services/name_count")
    fun getServiceNameAncCount(): Observable<Response<ArrayList<ServiceNameAndCount>>>

    @GET("/api/service/{name}")
    fun getServicesByName(@Path("name") name: String)
  }
}