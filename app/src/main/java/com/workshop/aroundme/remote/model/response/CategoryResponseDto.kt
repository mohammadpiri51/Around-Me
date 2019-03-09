package com.workshop.aroundme.remote.model.response

data class CategoryResponseDto(
    val meta: CategoryMeta?,
    val response: Map<String, List<CategoryDto>>?
)

data class CategoryMeta(
    val code: Int?,
    val msg: Any?,
    val notification: Int?
)


data class CategoryDto(
    val icon: String?,
    val id: Int?,
    val name: String?,
    val parent: Int?
)

