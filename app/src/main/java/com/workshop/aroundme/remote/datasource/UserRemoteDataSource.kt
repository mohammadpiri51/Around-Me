package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.remote.model.UserLoginItem
import com.workshop.aroundme.remote.model.UserRegisterItem
import com.workshop.aroundme.remote.model.response.UserResponseModel
import com.workshop.aroundme.remote.service.UserService

class UserRemoteDataSource(private val userService: UserService) {

    fun registerUser(userRegisterItem: UserRegisterItem): UserResponseModel? {
        return userService.registerUser(userRegisterItem)
    }

    fun loginUser(userLoginItem: UserLoginItem): UserResponseModel? {
        val test = userService.loginUser(userLoginItem)
        return userService.loginUser(userLoginItem)
    }
}