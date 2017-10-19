package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.entity.Plants
import com.chengtao.wisdomgarden.entity.Route
import com.chengtao.wisdomgarden.entity.Service
import com.chengtao.wisdomgarden.entity.ServiceNameAndCount
import com.chengtao.wisdomgarden.entity.Sight
import com.chengtao.wisdomgarden.entity.User
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
interface UserAPI {
  @POST("/api/user/login")
  @FormUrlEncoded
  fun login(@Field("userName") userName: String, @Field(
      "password") password: String): Observable<Response<User>>

  @POST("/api/user/register")
  @FormUrlEncoded
  fun register(@Field("userName") userName: String, @Field(
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
  fun getServicesByName(@Path("name") name: String): Observable<Response<ArrayList<Service>>>
}