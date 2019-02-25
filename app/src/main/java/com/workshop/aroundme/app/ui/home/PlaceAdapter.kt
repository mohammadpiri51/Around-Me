package com.workshop.aroundme.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.PlaceEntity

class PlaceAdapter(
    private val items: List<PlaceEntity>,
    private val onPlaceListItemClickListener: OnPlaceListItemClickListener
) : RecyclerView.Adapter<PlaceViewHolder>() {
    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(items[position], onPlaceListItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_list_item, parent, false)
        return PlaceViewHolder(view)
    }
}