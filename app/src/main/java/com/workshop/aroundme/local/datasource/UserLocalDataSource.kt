package com.workshop.aroundme.local.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import com.workshop.aroundme.data.model.UserEntity

class UserLocalDataSource(private val sharedPreferences: SharedPreferences) {

    fun addLogin(user: UserEntity) {
        val userData = Gson().toJson(user, UserEntity::class.java)
        sharedPreferences.edit().putString(KEY_USER, userData).apply()
    }

    fun logout() {
        sharedPreferences.edit().remove(KEY_USER).apply()
    }

    fun getUser(): UserEntity? {
        val userData = sharedPreferences.getString(KEY_USER, null)
        return Gson().fromJson(userData, UserEntity::class.java)
    }


    companion object {
        const val KEY_USER = "user"
    }
}