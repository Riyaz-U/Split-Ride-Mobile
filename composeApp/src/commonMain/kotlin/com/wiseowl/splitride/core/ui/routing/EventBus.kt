package com.wiseowl.splitride.core.ui.routing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class EventBusImpl: EventBus {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val channel = Channel<Intent>()
    override fun push(intent: Intent) {
        scope.launch { channel.send(intent) }
    }
    override fun subscribe(): Channel<Intent> = channel
}

interface EventBus{
    fun push(intent: Intent)
    fun subscribe(): Channel<Intent>
}