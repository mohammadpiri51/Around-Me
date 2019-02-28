package com.workshop.aroundme.remote

import io.github.rybalkinsd.kohttp.dsl.httpPost
import okhttp3.Response
import java.net.URL

class NetworkManager {

    fun get(url: String): String {
        return URL(url)
            .openStream()
            .bufferedReader()
            .use {
                it.readText()
            }
    }

    fun postByJson(url_scheme:String,usr_host:String,url_path:String,jsonModel:String):Response{
        return httpPost {
                    scheme = url_scheme
                    host = usr_host
                    path = url_path

                    // param { ... }
                    // header { ... }

            body("application/json") {
                string(jsonModel)
            }

                }
    }
}