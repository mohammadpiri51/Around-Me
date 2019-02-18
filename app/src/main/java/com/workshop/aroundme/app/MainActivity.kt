package com.workshop.aroundme.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.PlaceRepository
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.datasource.PlaceDataSource
import com.workshop.aroundme.remote.service.PlaceService

class MainActivity : AppCompatActivity(), OnPlaceListItemClickListener {
    override fun onPlaceClick(placeEntity: PlaceEntity) {
//        Toast.makeText(this,"${placeEntity.name} is selected",Toast.LENGTH_SHORT).show()
        val addressUri = Uri.parse("geo:0,0?q=${placeEntity.location}")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("AroundMe", "Can't show this location!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val placeRepository = PlaceRepository(PlaceDataSource(PlaceService(NetworkManager())))
        placeRepository.getFeaturedPlaces(::onFeaturedPlacesReady)

    }

    private fun onFeaturedPlacesReady(list: List<PlaceEntity>?) = runOnUiThread {
        var recyclerView: RecyclerView = findViewById(R.id.recyclerView_place)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlaceAdapter(list ?: emptyList(), this)
    }
}
