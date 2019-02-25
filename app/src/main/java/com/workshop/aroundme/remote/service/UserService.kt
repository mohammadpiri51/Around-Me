package com.workshop.aroundme.remote.service

import com.google.gson.Gson
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.model.UserRegisterItem
import com.workshop.aroundme.remote.model.response.UserResponseModel
import io.github.rybalkinsd.kohttp.dsl.httpPost
import okhttp3.Response


class UserService(private val networkManager: NetworkManager) {

    fun registerUser(userRegisterItem: UserRegisterItem): UserResponseModel {
        val test = ""
        val response: Response = httpPost {
            scheme = URL_SCHEME
            host = URL_HOST
            path = URL_PATH

            // param { ... }
            // header { ... }

            body {
                json {
                    "fullName" to userRegisterItem.fullName
                    "email" to userRegisterItem.email
                    "password" to userRegisterItem.passWord
                }
            }

        }
        return UserResponseModel(
            code = response.code(),
            message = response.message(),
            userRegisterItem = if (response.code() == 200) Gson().fromJson(
                response?.body()?.string(),
                UserRegisterItem::class.java
            ) else null
        )
    }

    companion object {
        const val URL_SCHEME = "https"
        const val URL_HOST = "restapis.xyz"
        const val URL_PATH = "/around-me/v1/user/register"
    }
}