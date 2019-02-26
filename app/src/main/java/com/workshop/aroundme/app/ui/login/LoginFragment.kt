package com.workshop.aroundme.app.ui.login


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.home.PlaceFragment
import com.workshop.aroundme.data.Mapper.toUserEntity
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.remote.model.response.UserResponseModel


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

        val emailEditText = view.findViewById(R.id.txt_email) as EditText
        val passwordEditText = view.findViewById(R.id.txt_password) as EditText
        val loginBtn = view.findViewById(R.id.btn_login) as Button
        loginBtn.setOnClickListener {
            Injector.provideUserRepository(requireContext()).loginUser(
                UserEntity(
                    fullName = "",
                    email = emailEditText.text.toString(),
                    passWord = passwordEditText.text.toString()
                ), ::onUserLogin
            )
        }

        return view
    }

    private fun onUserLogin(userResponseModel: UserResponseModel?) {
        activity?.runOnUiThread {
            if (userResponseModel?.code == 200) {
                Injector.provideUserRepository(requireContext()).addLogin(userResponseModel.toUserEntity())
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, PlaceFragment())
                    ?.commit()
            } else {
                showAlert(getString(R.string.error), getString(R.string.invalid_username_or_password))
            }
        }
    }

    private fun showAlert(title: String, text: String) {
        AlertDialog.Builder(view?.context)
            .setTitle(title)
            .setMessage(text)
            .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }

}
