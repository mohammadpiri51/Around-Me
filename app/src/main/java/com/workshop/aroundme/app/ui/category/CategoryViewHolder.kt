package com.workshop.aroundme.app.ui.category

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.workshop.aroundme.R
import com.workshop.aroundme.app.model.CategoryItem

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val categoryNameTextView: TextView = itemView.findViewById(R.id.txt_categoryName)
    private val categoryImage: ImageView = itemView.findViewById(R.id.image_categoryIcon)


    fun bind(
        category: CategoryItem,
        onCategoryListItemClickListener: OnCategoryListItemClickListener
    ) {
        categoryNameTextView.text = category.name
        Picasso.get().load(category.iconUrl).into(categoryImage)

        itemView.setOnClickListener {
            onCategoryListItemClickListener.onCategoryClick(category)
        }
    }
}