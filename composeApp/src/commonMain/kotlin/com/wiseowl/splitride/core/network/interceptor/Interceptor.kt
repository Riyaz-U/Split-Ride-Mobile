package com.wiseowl.splitride.core.network.interceptor

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.Sender
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder

abstract class Interceptor{
    fun build(client: HttpClient): HttpClient{
        client.plugin(HttpSend).intercept{
            intercept(it)
        }
        return client
    }

    abstract suspend fun Sender.intercept(requestBuilder: HttpRequestBuilder): HttpClientCall
}

fun HttpClient.addInterceptor(
    interceptor: Interceptor
): HttpClient{
    return interceptor.build(this)
}