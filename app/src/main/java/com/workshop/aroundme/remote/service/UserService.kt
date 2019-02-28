package com.workshop.aroundme.remote.service

import com.google.gson.Gson
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.model.UserLoginItem
import com.workshop.aroundme.remote.model.UserRegisterItem
import com.workshop.aroundme.remote.model.response.UserResponseModel
import io.github.rybalkinsd.kohttp.dsl.httpPost
import io.github.rybalkinsd.kohttp.util.Json
import io.github.rybalkinsd.kohttp.util.json
import okhttp3.Response


class UserService(private val networkManager: NetworkManager) {

    fun registerUser(userRegisterItem: UserRegisterItem): UserResponseModel {
        val jsonModel = json {
            "fullName" to userRegisterItem.fullName
            "email" to userRegisterItem.email
            "password" to userRegisterItem.passWord
        }
        val response: Response = networkManager.postByJson(URL_SCHEME, URL_HOST, URL_REGISTERPATH,jsonModel)

        return UserResponseModel(
            code = response.code(),
            message = response.message(),
            userLoginItem = null,
            userRegisterItem = if (response.code() == 200) Gson().fromJson(
                response?.body()?.string(),
                UserRegisterItem::class.java
            ) else null
        )
    }

    fun loginUser(userLoginItem: UserLoginItem): UserResponseModel {

        val jsonModel = json {
            "email" to userLoginItem.email
            "password" to userLoginItem.passWord
        }
        val response: Response = networkManager.postByJson(URL_SCHEME, URL_HOST, URL_LOGINPATH,jsonModel)

        return UserResponseModel(
            code = response.code(),
            message = response.message(),
            userRegisterItem = null,
            userLoginItem = if (response.code() == 200) Gson().fromJson(
                response?.body()?.string(),
                UserLoginItem::class.java
            ) else null
        )
    }

    companion object {
        const val URL_SCHEME = "https"
        const val URL_HOST = "restapis.xyz"
        const val URL_REGISTERPATH = "/around-me/v1/user/register"
        const val URL_LOGINPATH = "/around-me/v1/user/login"
    }
}