package com.workshop.aroundme.app.ui.category


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CategoryDetailFragment : Fragment() {

    private var categoryName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryName = arguments?.getString(KEY_CATEGORY_NAME)
        return inflater.inflate(R.layout.fragment_category_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        var categoryNameTextView = view?.findViewById<TextView>(R.id.txv_category_name)
        categoryNameTextView.text = categoryName

    }

    companion object {
        fun newInstance(categoryName: String?): CategoryDetailFragment {
            return CategoryDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_CATEGORY_NAME, categoryName)
                }
            }
        }

        const val KEY_CATEGORY_NAME = "CATEGORY_NAME"

    }
}
