package com.wiseowl.splitride.core.network.interceptor

import io.ktor.client.HttpClient

fun HttpClient.addInterceptor(
    interceptor: (HttpClient) -> Unit
): HttpClient{
    interceptor(this)
    return this
}