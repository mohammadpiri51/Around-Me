package com.workshop.aroundme.data

import com.workshop.aroundme.data.mapper.toParentCategoryEntity
import com.workshop.aroundme.data.model.category.CategoryEntity
import com.workshop.aroundme.data.model.category.ParentCategoryEntity
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSource

class CategoryRepository(private val categoryDataSource: CategoryRemoteDataSource) {

    fun getCategories(success: (List<ParentCategoryEntity>?) -> Unit, searchQuery: String = "") {
        Thread {
            val result = categoryDataSource.getCategories()
                ?.map { it.toParentCategoryEntity() }
                ?.filter { parentCategory -> filterParentCategories(parentCategory, searchQuery) }
                ?.map { parentCategory -> mapToParentCategoryEntity(parentCategory, searchQuery) }

            success(result)
        }.start()

    }

    private fun filterParentCategories(parentCategory: ParentCategoryEntity, searchQuery: String): Boolean {
        return searchQuery.isEmpty() ||
                parentCategory.categories?.any { categoryEntity ->
                    categoryEntity.name?.contains(
                        searchQuery
                    ) ?: false
                } ?: false
    }

    private fun mapToParentCategoryEntity(parentCategory: ParentCategoryEntity, searchQuery: String): ParentCategoryEntity {
        return ParentCategoryEntity(
            id = null,
            name = parentCategory.name,
            categories = parentCategory.categories?.filter { categoryEntity ->
                filterCategory(categoryEntity, searchQuery)
            }
        )
    }

    private fun filterCategory(categoryEntity: CategoryEntity, searchQuery: String): Boolean =
        categoryEntity.name?.contains(searchQuery) ?: false

}
