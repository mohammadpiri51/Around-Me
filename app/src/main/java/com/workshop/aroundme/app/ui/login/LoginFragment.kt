package com.workshop.aroundme.app.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val registerTextView = view.findViewById(R.id.lbl_register) as TextView
        registerTextView.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.content_frame, RegisterFragment())
                ?.commit()
        }
        return view
    }


}
