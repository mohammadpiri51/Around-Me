package com.workshop.aroundme.app.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.Utilty
import com.workshop.aroundme.app.ui.home.PlaceFragment
import com.workshop.aroundme.data.mapper.toUserEntity
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.remote.model.response.UserResponseModel

class RegisterFragment : Fragment() {

    private var fullNameEditText: EditText? = null
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var registerBtn: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        fullNameEditText = view.findViewById(R.id.txt_fullName)
        emailEditText = view.findViewById(R.id.txt_email)
        passwordEditText = view.findViewById(R.id.txt_password)
        registerBtn = view.findViewById(R.id.btn_register)

        registerBtn?.setOnClickListener {

            if (Utilty.isInternetAvailable(requireContext())) {


                if (emailEditText?.text.toString().isNullOrEmpty() ||
                    passwordEditText?.text.toString().isNullOrEmpty() ||
                    fullNameEditText?.text.toString().isNullOrEmpty()
                )
                    Utilty.showAlert(requireContext(), getString(R.string.error), getString(R.string.fill_all_fields))
                else if (!Utilty.isEmailValid(emailEditText?.text.toString()))
                    Utilty.showAlert(requireContext(), getString(R.string.error), getString(R.string.email_is_wrong))
                else {
                    Injector.provideUserRepository(requireContext()).registerUser(
                        UserEntity(
                            fullName = fullNameEditText?.text.toString(),
                            email = emailEditText?.text.toString(),
                            passWord = passwordEditText?.text.toString()
                        ), ::onUserRegister
                    )
                }

            } else {
                Utilty.showAlert(
                    requireContext(),
                    getString(R.string.error),
                    getString(R.string.internet_connection_error)
                )
            }


        }


    }

    private fun onUserRegister(userResponseModel: UserResponseModel?) {
        activity?.runOnUiThread {
            when {
                userResponseModel?.code == 200 -> {
                    Injector.provideUserRepository(requireContext()).addLogin(userResponseModel.toUserEntity())
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.content_frame, PlaceFragment())
                        ?.commit()
                }
                userResponseModel?.code == 400 -> Utilty.showAlert(
                    requireContext(),
                    getString(R.string.error),
                    getString(R.string.there_is_already_a_user_with_this_info)
                )
                else -> Utilty.showAlert(
                    requireContext(),
                    getString(R.string.error),
                    getString(R.string.one_of_fields_is_invalid)
                )
            }
        }
    }

}



