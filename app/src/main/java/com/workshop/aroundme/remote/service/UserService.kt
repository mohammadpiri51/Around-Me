package com.workshop.aroundme.remote.service

import com.google.gson.Gson
import com.workshop.aroundme.remote.model.UserLoginItem
import com.workshop.aroundme.remote.model.UserRegisterItem
import com.workshop.aroundme.remote.model.response.UserResponseModel
import io.github.rybalkinsd.kohttp.dsl.httpPost
import okhttp3.Response


class UserService() {

    fun registerUser(userRegisterItem: UserRegisterItem): UserResponseModel {
        val response: Response = httpPost {
            scheme = URL_SCHEME
            host = URL_HOST
            path = URL_REGISTERPATH

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
            userLoginItem = null,
            userRegisterItem = if (response.code() == 200) Gson().fromJson(
                response?.body()?.string(),
                UserRegisterItem::class.java
            ) else null
        )
    }

    fun loginUser(userLoginItem: UserLoginItem): UserResponseModel {
        val response: Response = httpPost {
            scheme = URL_SCHEME
            host = URL_HOST
            path = URL_LOGINPATH

            // param { ... }
            // header { ... }

            body {
                json {
                    "email" to userLoginItem.email
                    "password" to userLoginItem.passWord
                }
            }

        }
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