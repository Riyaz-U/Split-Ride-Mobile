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
        return when(T::class){
            Int::class -> getKVault().int(key)
            String::class -> getKVault().string(key)
            Boolean::class -> getKVault().bool(key)
            Long::class -> getKVault().long(key)
            Float::class -> getKVault().float(key)
            Double::class -> getKVault().double(key)
            ByteArray::class -> getKVault().data(key)
            else -> return null
        } as T?
    }
}
