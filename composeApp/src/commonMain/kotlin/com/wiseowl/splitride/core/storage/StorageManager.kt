package com.wiseowl.splitride.core.storage

import com.liftric.kvault.KVault

class StorageManager(
    private val kVault: KVault
) {
    fun getKVault() = kVault

    fun <T> save(key: String, value: T) {
        when(value){
            is Int -> kVault.set(key, value)
            is String -> kVault.set(key, value)
            is Long -> kVault.set(key, value)
            is Float -> kVault.set(key, value)
            is Double -> kVault.set(key, value)
            is ByteArray -> kVault.set(key, value)
            is Boolean -> kVault.set(key, value)
            else -> throw IllegalArgumentException("value of type: ${value!!::class.simpleName} cannot be saved")
        }
    }

    inline fun <reified T> get(key: String): T?{
        return when{
            T::class.isInstance(Int) -> getKVault().int(key) as Int
            T::class.isInstance(String) -> getKVault().int(key) as String
            T::class.isInstance(Boolean) -> getKVault().int(key) as Boolean
            T::class.isInstance(Long) -> getKVault().int(key) as Long
            T::class.isInstance(Float) -> getKVault().int(key) as Float
            T::class.isInstance(Double) -> getKVault().int(key) as Double
            T::class.isInstance(ByteArray::class) -> getKVault().int(key) as ByteArray
            else -> return null
        } as T?
    }
}
