package com.workshop.aroundme.app.model

data class CategoryItem(
    val isParent: Boolean,
    val id: Int?,
    val parentId: Int?,
    val name: String?,
    val iconUrl: String?
)