package com.workshop.aroundme.app

import android.content.Context
import android.content.SharedPreferences
import com.workshop.aroundme.data.UserRepository
import com.workshop.aroundme.local.datasource.UserLocalDataSource
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.datasource.UserRemoteDataSource
import com.workshop.aroundme.remote.service.UserService

object Injector {

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepository(
            UserRemoteDataSource(
                UserService(NetworkManager())
            ),
            UserLocalDataSource(provideDefaultSharedPref(context))
        )
    }

    private fun provideDefaultSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
    }
}