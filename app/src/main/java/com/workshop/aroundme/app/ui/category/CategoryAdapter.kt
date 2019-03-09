package com.workshop.aroundme.app.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.model.CategoryItem
import java.util.*

class CategoryAdapter(
    private val items: List<CategoryItem>,
    private val onCategoryListItemClickListener: OnCategoryListItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var categoriesSearchList: List<CategoryItem> = Collections.emptyList()

    init {
        this.categoriesSearchList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_PARENT_CATEGORIES_ITEM -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_parent_category_list_item, parent, false)
                ParentCategoryViewHolder(view)
            }
            ITEM_TYPE_CATEGORIES_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_list_item, parent, false)
                CategoryViewHolder(view)
            }
            else -> {
                throw IllegalStateException("Invalid view type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (categoriesSearchList[position].isParent) {
            ITEM_TYPE_PARENT_CATEGORIES_ITEM
        } else {
            ITEM_TYPE_CATEGORIES_ITEM
        }
    }

    override fun getItemCount() = categoriesSearchList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_PARENT_CATEGORIES_ITEM -> {
                (holder as ParentCategoryViewHolder).bind(categoriesSearchList[position])
            }
            ITEM_TYPE_CATEGORIES_ITEM -> {
                (holder as CategoryViewHolder).bind(categoriesSearchList[position], onCategoryListItemClickListener)
            }
        }
    }

    companion object {
        const val ITEM_TYPE_PARENT_CATEGORIES_ITEM = 0
        const val ITEM_TYPE_CATEGORIES_ITEM = 1

    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val query = charSequence.toString()
                categoriesSearchList = if (query.isEmpty()) {
                    items
                } else {
                    getFilteredList(query)
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = categoriesSearchList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                categoriesSearchList = filterResults.values as ArrayList<CategoryItem>
                notifyDataSetChanged()
            }
        }
    }

    private fun getFilteredList(charString: String): List<CategoryItem> {
        val filteredList = mutableListOf<CategoryItem>()
        for (row in items) {
            if (!row.isParent && row.name!!.toLowerCase().contains(charString.toLowerCase())) {
                if (!filteredList.any { item -> item.id == row.parentId }) {
                    val parent = items.firstOrNull { item -> item.id == row.parentId }
                    if (parent != null)
                        filteredList.add(parent)
                }
                filteredList.add(row)
            }
        }
        return filteredList
    }

}