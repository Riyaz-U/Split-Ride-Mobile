package com.wiseowl.splitride.core.storage

class UserDetailStorage(
    private val storageManager: StorageManager
) {
    fun markOnboardingCompleted() = storageManager.save(VISITED_ONBOARDING, true)
    fun hasOnboardingCompleted() = storageManager.get<Boolean>(VISITED_ONBOARDING)

    fun getActiveStatus(): Boolean? = storageManager.get<Boolean>(ACTIVE_STATUS)
    fun setActiveStatus(isActive: Boolean) = storageManager.save(ACTIVE_STATUS, isActive)


    companion object{
        const val VISITED_ONBOARDING = "visited_onboarding"
        const val ACTIVE_STATUS = "active_status"
    }
}