package com.workshop.aroundme.data

import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.datasource.PlaceDataSource

class PlaceRepository(private val placeDataSource: PlaceDataSource) {

    fun getFeaturedPlaces(success: (List<PlaceEntity>?) -> Unit, searchQuery: String = "") {
        Thread {
            val result = placeDataSource.getFeaturedPlaces()?.map {
                PlaceEntity(it.full_name, it.address, it.like_count, it.latlng)
            }
                ?.filter { placeEntity -> searchQuery?.isNullOrEmpty() || placeEntity.name?.contains(searchQuery) ?: false }
            success(result)
        }.start()
    }
}