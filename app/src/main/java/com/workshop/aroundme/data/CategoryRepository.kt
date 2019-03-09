package com.workshop.aroundme.data

import com.workshop.aroundme.data.mapper.toParentCategoryEntity
import com.workshop.aroundme.data.model.category.ParentCategoryEntity
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSource

class CategoryRepository(private val categoryDataSource: CategoryRemoteDataSource) {

    fun getCategories(success: (List<ParentCategoryEntity>?) -> Unit, searchQuery: String = "") {
        Thread {
            val result = categoryDataSource.getCategories()?.map {
                it.toParentCategoryEntity()
            }
                ?.filter { parentCategory ->
                    filterParentCategories(parentCategory, searchQuery)
                }?.map(fun(parentCategory: ParentCategoryEntity): ParentCategoryEntity {
                    return filterCategories(parentCategory, searchQuery)
                })

            success(result)
        }.start()

    }

    private fun filterParentCategories(parentCategory: ParentCategoryEntity, searchQuery: String): Boolean {
        return searchQuery?.isNullOrEmpty() ||
                parentCategory.categories?.any { categoryEntity ->
                    categoryEntity.name?.contains(
                        searchQuery
                    ) ?: false
                } ?: false
    }

    private fun filterCategories(parentCategory: ParentCategoryEntity, searchQuery: String): ParentCategoryEntity {
        return ParentCategoryEntity(
            id = null,
            name = parentCategory.name,
            categories = parentCategory.categories?.filter { categoryEntity ->
                categoryEntity.name?.contains(
                    searchQuery
                ) ?: false
            }
        )
    }

}
