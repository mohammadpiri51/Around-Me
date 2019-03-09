package com.workshop.aroundme.remote.service

import com.google.gson.Gson
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.model.response.CategoryResponseDto

class CategoryService(private val networkManager: NetworkManager) {

    fun getFeaturedPlacesResponse(): CategoryResponseDto {
        val rawData = networkManager.get(URL_CATEGORY)
        return Gson().fromJson(rawData, CategoryResponseDto::class.java)
    }

    companion object {
        const val URL_CATEGORY = "https://restapis.xyz/around-me/v1/category"
    }
}

