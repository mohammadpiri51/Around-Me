package com.workshop.aroundme.remote.model.response

import com.workshop.aroundme.remote.model.UserRegisterItem

data class UserResponseModel(
    val code:Int,
    val message:String,
    val userRegisterItem: UserRegisterItem?
)