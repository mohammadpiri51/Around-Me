package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.remote.model.response.CategoryDto
import com.workshop.aroundme.remote.service.CategoryService

class CategoryRemoteDataSource(private val categoryService: CategoryService) {

    fun getCategories(): Map<String, List<CategoryDto>>? {
        return categoryService.getFeaturedPlacesResponse()
            .response
    }
}

