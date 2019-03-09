package com.workshop.aroundme.app.ui.category


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.mapper.toCategoryItemList
import com.workshop.aroundme.app.model.CategoryItem
import com.workshop.aroundme.data.model.category.ParentCategoryEntity


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CategoryFragment : Fragment(), OnCategoryListItemClickListener, SearchView.OnQueryTextListener {


    private var categoryAdapter: CategoryAdapter? = null
    private val categoryRepository = Injector.provideCategoryRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val categorySearchView = view.findViewById<SearchView>(R.id.searchView_category)
        categorySearchView.setOnQueryTextListener(this)
        categorySearchView.isFocusable = false
        categorySearchView.isIconified = false
        categorySearchView.clearFocus()
        categoryRepository.getCategories(::onCategoriesReady)

    }

    private fun onCategoriesReady(list: List<ParentCategoryEntity>?) {
        activity?.runOnUiThread {
            var recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView_category)
            recyclerView?.visibility = View.VISIBLE
            val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar_category)
            progressBar?.visibility = View.GONE
            recyclerView?.layoutManager = LinearLayoutManager(requireContext())
            categoryAdapter = CategoryAdapter(list?.toCategoryItemList() ?: emptyList(), this)
            recyclerView?.adapter = categoryAdapter
        }
    }

    override fun onCategoryClick(categoryItem: CategoryItem) {
        val categoryDetailFragment = CategoryDetailFragment.newInstance(categoryItem.name)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, categoryDetailFragment)
            ?.addToBackStack(null)
            ?.commit()

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        categoryAdapter!!.filter.filter(newText)
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        categoryAdapter!!.filter.filter(query)
        return false
    }


}
