package com.wiseowl.splitride.core.storage

class AuthenticationStorage(
    private val storageManager: StorageManager
) {
    fun saveToken(token: String) = storageManager.save(ACCESS_TOKEN_KEY, token)
    fun getToken(): String? = storageManager.get(ACCESS_TOKEN_KEY)

    fun saveRefreshToken(token: String) = storageManager.save(REFRESH_TOKEN_KEY, token)
    fun getRefreshToken(): String? = storageManager.get(REFRESH_TOKEN_KEY)

    fun saveName(name: String) = storageManager.save(NAME, name)
    fun getName(): String? = storageManager.get(NAME)

    companion object{
        const val ACCESS_TOKEN_KEY = "access_token"
        const val REFRESH_TOKEN_KEY = "access_token"
        const val NAME = "name"
    }
}