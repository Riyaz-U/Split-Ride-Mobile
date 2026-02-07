package com.wiseowl.splitride.core.network

import com.wiseowl.splitride.core.network.response.SplitRideResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class ApiService {
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

        HttpResponseValidator {
            validateResponse { response ->
                if (response.status.value == 401) {//TODO: Refresh token}
                }
            }
        }
    }

//    fun getClient(): HttpClient = client
//    fun getBaseUrl(): String = baseUrl
//
//    fun getHeader(): List<Pair<String, String>>{
//        val headers = mutableListOf<Pair<String, String>>()
//        ServiceLocator.getServices().sessionStorageManager.getAccessToken()?.let {
//            headers.add(Pair("Authorization", "Bearer $it"))
//        }
//        return headers
//    }
//
//    suspend inline fun <reified T, reified R> post(
//        endPoint: EndPoint,
//        body: R,
//    ): SplitRideResponse<T> = safeRequest{
//        getClient().post(getBaseUrl() + endPoint.path) {
//            parameters {
//
//            }
//            setBody(body)
//            contentType(ContentType.Application.Json)
//            headers {
//                getHeader().forEach {
//                    set(it.first, it.second)
//                }
//            }
//        }.body<SplitRideResponse<T>>() as SplitRideResponse<T>
//    }
//
//    suspend inline fun <reified T> get(
//        endPoint: EndPoint,
//        vararg queryParameters: Pair<String, Any> = emptyArray(),
//        pathParameter: PathParam? = null
//    ): SplitRideResponse<T> = safeRequest{
//        val url = getUrl(endPoint, queryParameters = queryParameters, pathParam = pathParameter)
//        val response = getClient().get(url){
//            headers {
//                getHeader().forEach {
//                    set(it.first, it.second)
//                }
//            }
//            //NEED to remove
//            timeout {
//                requestTimeoutMillis = 20000
//                socketTimeoutMillis = 20000
//            }
//        }.body<SplitRideResponse<T>>() as SplitRideResponse<T>
//        response
//    }
//
//    suspend inline fun <T> safeRequest(
//        crossinline block: suspend () -> SplitRideResponse<T>
//    ): SplitRideResponse<T> {
//        return try {
//            block()
//        } catch (e: AccessTokenExpiredException) {
//            if (refreshTokens()) {
//                block() // retry once
//            } else {
//                ServiceLocator.getServices().sessionStorageManager.clearSession()
//                RootReducer.push(Intent.Navigate(Authentication))
//                return SplitRideResponse(
//                    success = false,
//                    HttpStatusCode.Unauthorized.value,
//                    null,
//                    null
//                )
//            }
//        }
//    }
//
//    fun refreshTokens(): Boolean = runBlocking {
//        runCatching {
//            val currentSession = ServiceLocator.getServices().sessionStorageManager.getSession()!!
//            val serverResponse = post<AuthenticationDTO, AuthenticationDTO>(
//                endPoint = AuthenticationEndPoint.RefreshToken,
//                body = AuthenticationDTO(
//                    currentSession.accessToken,
//                    currentSession.refreshToken
//                )
//            ).data!!
//            val session = SessionStorageManager.Session(
//                email = JwtTokenUtil.getEmail(serverResponse.accessToken),
//                accessToken = serverResponse.accessToken,
//                refreshToken = serverResponse.refreshToken
//            )
//            ServiceLocator.getServices().sessionStorageManager.saveSession(session)
//            true
//        }.getOrDefault(false)
//    }
//
//    class PathParam(
//        val name: String,
//        val value: String
//    )
//
//    fun getUrl(
//        endPoint: EndPoint,
//        vararg queryParameters: Pair<String, Any> = emptyArray(),
//        pathParam: PathParam? = null
//    ): String {
//        val url = buildString {
//            append(getBaseUrl())
//            if (pathParam != null) {
//                append(endPoint.path.replace("{${pathParam.name}}", pathParam.value))
//            } else append(endPoint.path)
//            if (queryParameters.isNotEmpty()) {
//                append("?" + queryParameters.joinToString("&") { "${it.first}=${it.second}" })
//            }
//        }
//        return url
//    }
}