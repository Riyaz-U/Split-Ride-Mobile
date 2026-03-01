package com.wiseowl.splitride.core.network

import com.wiseowl.splitride.core.network.interceptor.Interceptor
import com.wiseowl.splitride.core.network.interceptor.addInterceptor
import com.wiseowl.splitride.core.network.response.SplitRideResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiService(authInterceptor: Interceptor) {
    private val BASE_URL = ""
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true // Optional: for readable JSON output
                ignoreUnknownKeys = true // Optional: to ignore unknown fields in JSON
                isLenient = true
                explicitNulls = false
            })
        }
        install(HttpTimeout)
    }.addInterceptor(authInterceptor)


    fun getClient(): HttpClient = client
    fun getBaseUrl(): String = BASE_URL


    suspend inline fun <reified T, reified R> post(
        endPoint: EndPoint,
        body: R,
    ): SplitRideResponse<T>{
        return getClient().post(getBaseUrl() + endPoint.path) {
            setBody(body)
            contentType(ContentType.Application.Json)
        }.body<SplitRideResponse<T>>() as SplitRideResponse<T>
    }

    suspend inline fun <reified T> get(
        endPoint: EndPoint,
        vararg queryParameters: Pair<String, Any> = emptyArray(),
        pathParameter: PathParam? = null
    ): SplitRideResponse<T>{
        val url = getUrl(endPoint, queryParameters = queryParameters, pathParam = pathParameter)
        val response = getClient().get(url).body<SplitRideResponse<T>>() as SplitRideResponse<T>
        return response
    }

    class PathParam(
        val name: String,
        val value: String
    )

    fun getUrl(
        endPoint: EndPoint,
        vararg queryParameters: Pair<String, Any> = emptyArray(),
        pathParam: PathParam? = null
    ): String {
        val url = buildString {
            append(getBaseUrl())
            if (pathParam != null) {
                append(endPoint.path.replace("{${pathParam.name}}", pathParam.value))
            } else append(endPoint.path)
            if (queryParameters.isNotEmpty()) {
                append("?" + queryParameters.joinToString("&") { "${it.first}=${it.second}" })
            }
        }
        return url
    }
}