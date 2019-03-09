package com.workshop.aroundme.app.ui.home


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.category.CategoryFragment
import com.workshop.aroundme.app.ui.login.LoginFragment
import com.workshop.aroundme.data.PlaceRepository
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.datasource.PlaceDataSource
import com.workshop.aroundme.remote.service.PlaceService


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PlaceFragment : Fragment(), OnPlaceListItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.btn_search).setOnClickListener {
            val searchTextView = view.findViewById<EditText>(R.id.txt_search)
            val searchQuery: String = searchTextView.text.toString()
            if (!searchQuery.isNullOrEmpty()) {
                var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_place)
                recyclerView.visibility = View.GONE
                val progressBar = view.findViewById<ProgressBar>(R.id.loadingBar)
                progressBar?.visibility = View.VISIBLE
                val placeRepository = PlaceRepository(PlaceDataSource(PlaceService(NetworkManager())))
                placeRepository.getFeaturedPlaces(::onFeaturedPlacesReady, searchQuery)
            }
        }
        val placeRepository = PlaceRepository(PlaceDataSource(PlaceService(NetworkManager())))
        placeRepository.getFeaturedPlaces(::onFeaturedPlacesReady)
    }

    private fun onFeaturedPlacesReady(list: List<PlaceEntity>?) {
        activity?.runOnUiThread {
            var recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView_place)
            recyclerView?.visibility = View.VISIBLE
            val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar)
            progressBar?.visibility = View.GONE
            recyclerView?.layoutManager = LinearLayoutManager(requireContext())
            recyclerView?.adapter = PlaceAdapter(list ?: emptyList(), this)
        }
    }

    override fun onPlaceClick(placeEntity: PlaceEntity) {
        val addressUri = Uri.parse("geo:0,0?q=${placeEntity.location}")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        if (intent.resolveActivity(this.activity?.packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("AroundMe", "Can't show this location!")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_place_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuItem_logOut -> {
                Injector.provideUserRepository(requireContext()).logOut()
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, LoginFragment())
                    ?.commit()
            }

            R.id.menuItem_categories -> {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, CategoryFragment())
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
