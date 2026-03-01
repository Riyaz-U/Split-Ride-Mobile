package com.wiseowl.splitride.core.network.interceptor

import com.wiseowl.splitride.core.storage.AuthenticationStorage
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder

class AuthInterceptor(val authenticationStorage: AuthenticationStorage) : Interceptor() {
    override suspend fun Sender.intercept(requestBuilder: HttpRequestBuilder): HttpClientCall {
        //Attach Authentication token
        val accessToken = authenticationStorage.getToken()
        if (accessToken != null) requestBuilder.headers["Authorization"] = accessToken
        val originalCall = execute(requestBuilder)
        return if (originalCall.response.status.value == 401) originalCall
        else originalCall
    }
}

