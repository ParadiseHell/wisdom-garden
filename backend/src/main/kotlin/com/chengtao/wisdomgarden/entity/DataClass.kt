package com.chengtao.wisdomgarden.entity

/**
 * Created by chengtao on 10/18/17.
 */
data class ViewAndRouter constructor(val viewName: String, val viewRouter: String)

data class ServiceNameAndCount constructor(val name: String, val count: Int)

data class APIError constructor(val error: String)