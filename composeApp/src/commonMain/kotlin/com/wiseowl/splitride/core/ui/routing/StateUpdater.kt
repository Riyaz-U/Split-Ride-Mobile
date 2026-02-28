package com.wiseowl.splitride.core.ui.routing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class StateUpdater(
    private val eventBus: EventBus,
    private val scope: CoroutineScope,
    reducerScope: ReducerBuilder.() -> Unit
) {
    private val reducers = ReducerBuilder().apply(reducerScope).build()

    fun processEvent(intent: Intent) {
        val reducerForIntent = reducers[intent]
        if (reducerForIntent != null) {
            reducerForIntent(intent)
        } else {
            // EventBus.push is suspend; if you want fire-and-forget, decide where the coroutine scope lives.
            // Keeping existing behavior would require calling this from a coroutine.
            // For now, delegate to the caller to ensure it's called from a coroutine if needed.
            // (Most likely you'll make processEvent suspend.)
            //
            scope.launch { eventBus.push(intent) }
        }
    }
}

class ReducerBuilder{
    private val reducers = mutableMapOf<Intent, Intent.() -> Unit>()

    fun on(intent: Intent, reducer: Intent.() -> Unit){
        reducers[intent] = reducer
    }

    fun build(): Map<Intent, Intent.() -> Unit> = reducers
}