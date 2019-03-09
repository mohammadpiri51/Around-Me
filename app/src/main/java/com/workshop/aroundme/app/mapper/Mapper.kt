package com.workshop.aroundme.app.mapper

import com.workshop.aroundme.app.model.CategoryItem
import com.workshop.aroundme.data.model.category.CategoryEntity
import com.workshop.aroundme.data.model.category.ParentCategoryEntity

fun CategoryEntity.toCategoryItem() = CategoryItem(
    isParent = false,
    id = id,
    parentId = parentId,
    name = name,
    iconUrl = iconUrl
)

fun ParentCategoryEntity.toCategoryItem() = CategoryItem(
    isParent = true,
    id = categories?.first()?.parentId,
    parentId = null,
    name = name,
    iconUrl = ""
)

fun List<ParentCategoryEntity>.toCategoryItemList(): List<CategoryItem> {

    var categoryGeneralEntityList = arrayListOf<CategoryItem>()

    for (parentCategory in this) {
        categoryGeneralEntityList.add(parentCategory.toCategoryItem())
        for (category in parentCategory.categories ?: emptyList()) {
            categoryGeneralEntityList.add(category.toCategoryItem())
        }
    }

    return categoryGeneralEntityList
}