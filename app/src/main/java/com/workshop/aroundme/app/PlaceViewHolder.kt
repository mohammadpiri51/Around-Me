package com.workshop.aroundme.app

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.PlaceEntity
import kotlinx.android.synthetic.main.item_place_list_item.view.*

class PlaceViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

    private val placeNameTextView:TextView=itemView.findViewById(R.id.txt_placeName)
    private val addressTextView:TextView=itemView.findViewById(R.id.txt_address)
    private val likesCountTextView:TextView=itemView.findViewById(R.id.txt_likesCount)
    private val likesCountImageView:ImageView=itemView.findViewById(R.id.image_likes)

    fun bind(
        place:PlaceEntity,
        position:Int,
        onPlaceListItemClickListener: OnPlaceListItemClickListener){

        placeNameTextView.text = place.name
        addressTextView.text = place.address?:""
        if (place.likesCount!=null && place.likesCount>0){
            likesCountTextView.text = place.likesCount.toString()
        }else{
            likesCountTextView.visibility = View.GONE
            likesCountImageView.visibility = View.GONE
        }


//        if (position.rem(other = 2) == 0) {
//            placeNameTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
//            placeNameTextView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
//        }

        itemView.setOnClickListener{
            onPlaceListItemClickListener.onPlaceClick(place)
        }
    }
}