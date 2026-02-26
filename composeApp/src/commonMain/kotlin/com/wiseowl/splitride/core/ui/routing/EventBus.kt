package com.wiseowl.splitride.core.ui.routing

import kotlinx.coroutines.channels.Channel

class EventBus {
    private val channel = Channel<Intent>()
    suspend fun push(intent: Intent){
        channel.send(intent)
    }
    fun subscribe(): Channel<Intent> = channel
}