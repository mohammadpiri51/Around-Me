package com.workshop.aroundme.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.Utilty
import com.workshop.aroundme.app.ui.home.PlaceFragment
import com.workshop.aroundme.app.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Utilty.isInternetAvailable(this)) {
            val userRepository = Injector.provideUserRepository(this)
            if (userRepository.isLoggedIn()) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, PlaceFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, LoginFragment())
                    .commit()
            }
        } else {
            Utilty.showAlert(this, getString(R.string.error), getString(R.string.internet_connection_error))
        }


    }

}
