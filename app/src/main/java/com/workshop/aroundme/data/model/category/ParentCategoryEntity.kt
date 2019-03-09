package com.workshop.aroundme.data.model.category

data class ParentCategoryEntity(
    val id: Int?,
    val name:String?,
    val categories:List<CategoryEntity>?
)