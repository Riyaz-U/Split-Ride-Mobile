package com.wiseowl.splitride.ride.home.domain

import com.wiseowl.splitride.ride.home.domain.models.CommuteDetail
import com.wiseowl.splitride.ride.home.domain.models.RideGroup

interface HomeRepository {
    suspend fun getRecentCommute(): List<CommuteDetail>
    suspend fun getNearbyGroups(): List<RideGroup>
    fun getActiveStatus(): Boolean
    fun setActiveStatus(isActive: Boolean)
}