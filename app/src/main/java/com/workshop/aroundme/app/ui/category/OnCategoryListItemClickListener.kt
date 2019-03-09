package com.workshop.aroundme.app.ui.category

import com.workshop.aroundme.app.model.CategoryItem


interface OnCategoryListItemClickListener {
    fun onCategoryClick(categoryItem: CategoryItem)
}