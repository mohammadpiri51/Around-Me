package com.workshop.aroundme.data.Mapper

import com.workshop.aroundme.data.model.UserEntity
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

fun UserResponseModel.toUserEntity() = UserEntity(
    fullName = userRegisterItem?.fullName,
    email = userRegisterItem?.email,
    passWord = userRegisterItem?.passWord
)