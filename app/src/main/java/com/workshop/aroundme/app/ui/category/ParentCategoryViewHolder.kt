package com.workshop.aroundme.app.ui.category

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.model.CategoryItem

class ParentCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val parentCategoryNameTextView: TextView = itemView.findViewById(R.id.txt_parentCategoryName)


    fun bind(
        parentCategory: CategoryItem
    ) {
        parentCategoryNameTextView.text = parentCategory.name

    }
}

