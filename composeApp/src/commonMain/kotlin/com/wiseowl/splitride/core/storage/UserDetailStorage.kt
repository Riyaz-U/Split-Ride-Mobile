package com.wiseowl.splitride.core.storage

class UserDetailStorage(
    private val storageManager: StorageManager
) {
    fun markOnboardingCompleted() = storageManager.save(VISITED_ONBOARDING, true)
    fun hasOnboardingCompleted() = storageManager.get<Boolean>(VISITED_ONBOARDING)

    companion object{
        const val VISITED_ONBOARDING = "visited_onboarding"
    }
}