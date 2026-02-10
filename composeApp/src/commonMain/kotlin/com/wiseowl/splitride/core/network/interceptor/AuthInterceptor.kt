package com.wiseowl.splitride.core.network.interceptor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header

val authInterceptor: (HttpClient) -> Unit = { client ->
    client.plugin(HttpSend).intercept { request ->
        //Attach Authentication token
        if(/*authenticated*/ true) request.header("Authorization", "token")
        val originalCall = execute(request)
        if (originalCall.response.status.value == 401) {
            //TODO: Refresh token
            originalCall
        } else {
            originalCall
        }
    }
}


