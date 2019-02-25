package com.workshop.aroundme.app.ui.home

import com.workshop.aroundme.data.model.PlaceEntity

interface OnPlaceListItemClickListener {
    fun onPlaceClick(placeEntity: PlaceEntity)
}