package com.workshop.aroundme.data

import com.workshop.aroundme.data.mapper.toUserLoginItem
import com.workshop.aroundme.data.mapper.toUserRegisterItem
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.local.datasource.UserLocalDataSource
import com.workshop.aroundme.remote.datasource.UserRemoteDataSource
import com.workshop.aroundme.remote.model.response.UserResponseModel

class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) {
    fun registerUser(userEntity: UserEntity, success: (UserResponseModel?) -> Unit) {
        Thread {
            val result = userRemoteDataSource.registerUser(userEntity.toUserRegisterItem())

            success(result)
        }.start()
    }

    fun loginUser(userEntity: UserEntity, success: (UserResponseModel?) -> Unit) {
        Thread {
            val result = userRemoteDataSource.loginUser(userEntity.toUserLoginItem())

            success(result)
        }.start()
    }

    fun addLogin(user: UserEntity) {
        userLocalDataSource.addLogin(user)
    }

    fun logOut() {
        userLocalDataSource.logout()
    }


    fun isLoggedIn() = userLocalDataSource.getUser() != null
}