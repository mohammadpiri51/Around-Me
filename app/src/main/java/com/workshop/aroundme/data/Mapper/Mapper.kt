package com.workshop.aroundme.data.Mapper

import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.remote.model.UserLoginItem
import com.workshop.aroundme.remote.model.UserRegisterItem
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
    fullName = if (userRegisterItem != null) userRegisterItem?.fullName else "",
    email = if (userRegisterItem != null) userRegisterItem?.email else if (userLoginItem != null) userLoginItem?.email else "",
    passWord = if (userRegisterItem != null) userRegisterItem?.passWord else if (userLoginItem != null) userLoginItem?.passWord else ""
)