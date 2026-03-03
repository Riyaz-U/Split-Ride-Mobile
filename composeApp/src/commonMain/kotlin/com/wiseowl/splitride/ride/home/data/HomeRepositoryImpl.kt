package com.wiseowl.splitride.ride.home.data

import com.wiseowl.splitride.core.network.ApiService
import com.wiseowl.splitride.core.storage.UserDetailStorage
import com.wiseowl.splitride.ride.home.domain.HomeRepository
import com.wiseowl.splitride.ride.home.domain.models.CommuteDetail
import com.wiseowl.splitride.ride.home.domain.models.RideGroup

class HomeRepositoryImpl(
    private val apiService: ApiService,
    private val userDetailStorage: UserDetailStorage
): HomeRepository {
    override suspend fun getRecentCommute(): List<CommuteDetail> {
        return emptyList()
    }

    override suspend fun getNearbyGroups(): List<RideGroup> {
        return emptyList()
    }

    override fun getActiveStatus(): Boolean {
        return userDetailStorage.getActiveStatus() ?: true
    }

    override fun setActiveStatus(isActive: Boolean) {
        userDetailStorage.setActiveStatus(isActive)
    }
}