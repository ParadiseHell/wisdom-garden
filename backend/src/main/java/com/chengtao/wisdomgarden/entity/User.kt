package com.chengtao.wisdomgarden.entity;

class User {
  var id: Int? = null
  var name: String? = null

  constructor() {
    id = null
    name = null
  }

  constructor(id: Int, name: String) {
    this.id = id
    this.name = name
  }
}