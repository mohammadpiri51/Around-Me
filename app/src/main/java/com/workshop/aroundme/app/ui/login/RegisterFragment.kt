package com.workshop.aroundme.app.ui.login


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.home.PlaceFragment
import com.workshop.aroundme.data.Mapper.toUserEntity
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.remote.model.response.UserResponseModel

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val fullNameEditText = view.findViewById(R.id.txt_fullName) as EditText
        val emailEditText = view.findViewById(R.id.txt_email) as EditText
        val passwordEditText = view.findViewById(R.id.txt_password) as EditText
        val registerBtn = view.findViewById(R.id.btn_register) as Button
        registerBtn.setOnClickListener {
            Injector.provideUserRepository(requireContext()).registerUser(
                UserEntity(
                    fullName = fullNameEditText.text.toString(),
                    email = emailEditText.text.toString(),
                    passWord = passwordEditText.text.toString()
                ), ::onUserRegister
            )
        }
        return view
    }

    private fun onUserRegister(userResponseModel: UserResponseModel?) {
        activity?.runOnUiThread {
            if (userResponseModel?.code == 200) {
                Injector.provideUserRepository(requireContext()).addLogin(userResponseModel.toUserEntity())
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, PlaceFragment())
                    ?.commit()
            } else if (userResponseModel?.code == 400) {
                showAlert(getString(R.string.error), getString(R.string.there_is_already_a_user_with_this_info))
            } else {
                showAlert(getString(R.string.error), getString(R.string.one_of_fields_is_invalid))
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



