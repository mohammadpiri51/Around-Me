package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.remote.model.UserRegisterItem
import com.workshop.aroundme.remote.model.response.UserResponseModel
import com.workshop.aroundme.remote.service.UserService

class UserRemoteDataSource(private val userService: UserService) {

    fun registerUser(userRegisterItem: UserRegisterItem): UserResponseModel? {
        return userService.registerUser(userRegisterItem)
    }
}