package com.workshop.aroundme.app.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import com.workshop.aroundme.R
import java.util.regex.Pattern


object Utilty {
    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }


    fun isInternetAvailable(context: Context): Boolean {
        var status: String? = null
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi enabled"
                return true
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobile data enabled"
                return true
            }
        } else {
            status = "No internet is available"
            return false
        }
        return status.isNullOrEmpty()
    }

    fun showAlert(context: Context, title: String, text: String) = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(text)
        .setPositiveButton(context.getString(R.string.ok)) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        .create()
        .show()
}