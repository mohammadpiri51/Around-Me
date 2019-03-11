package com.workshop.aroundme.data.mapper

import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.data.model.category.CategoryEntity
import com.workshop.aroundme.data.model.category.ParentCategoryEntity
import com.workshop.aroundme.remote.model.UserLoginItem
import com.workshop.aroundme.remote.model.UserRegisterItem
import com.workshop.aroundme.remote.model.response.CategoryDto
import com.workshop.aroundme.remote.model.response.UserResponseModel

fun UserEntity.toUserRegisterItem() = UserRegisterItem(
    fullName = fullName ?: "",
    email = email ?: "",
    passWord = passWord ?: ""
)

fun UserRegisterItem.toUserEntity() = UserEntity(
    fullName = fullName,
    email = email,
    passWord = passWord

)

fun UserEntity.toUserLoginItem() = UserLoginItem(
    email = email ?: "",
    passWord = passWord ?: ""
)

fun UserResponseModel.toUserEntity() = UserEntity(
    fullName = if (userRegisterItem != null) userRegisterItem.fullName else "",
    email = if (userRegisterItem != null) userRegisterItem.email else if (userLoginItem != null) userLoginItem.email else "",
    passWord = if (userRegisterItem != null) userRegisterItem.passWord else if (userLoginItem != null) userLoginItem.passWord else ""
)

fun CategoryDto.toCategoryEntity() = CategoryEntity(
    id = id,
    parentId = parent,
    name = name,
    iconUrl = icon
)

fun Map.Entry<String, List<CategoryDto>>.toParentCategoryEntity() = ParentCategoryEntity(
    id = null,
    name = key,
    categories = value.map { categoryDto -> categoryDto.toCategoryEntity() }
)