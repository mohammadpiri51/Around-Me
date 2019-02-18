package com.workshop.aroundme.app

import com.workshop.aroundme.data.model.PlaceEntity

interface OnPlaceListItemClickListener {
    fun onPlaceClick(placeEntity: PlaceEntity)
}