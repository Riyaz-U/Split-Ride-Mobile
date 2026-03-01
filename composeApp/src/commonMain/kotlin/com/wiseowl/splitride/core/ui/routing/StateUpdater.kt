package com.wiseowl.splitride.core.ui.routing

class StateUpdater(
    private val eventBus: EventBus,
    reducerScope: ReducerBuilder.() -> Unit
) {
    private val reducers = ReducerBuilder().apply(reducerScope).build()

    fun processIntent(intent: Intent?) {
        if(intent==null) return
        val reducerHolder = reducers[intent::class.qualifiedName]
        if (reducerHolder != null) {
            reducerHolder.reducer.invoke(intent, this)
        } else {
            // EventBus.push is suspend; if you want fire-and-forget, decide where the coroutine scope lives.
            // Keeping existing behavior would require calling this from a coroutine.
            // For now, delegate to the caller to ensure it's called from a coroutine if needed.
            // (Most likely you'll make processEvent suspend.)
            //
            eventBus.push(intent)
        }
    }
}

class ReducerBuilder{
    val reducers = mutableMapOf<String, ReducerHolder<Intent>>()

    inline fun <reified I: Intent> on(noinline reducer: I.(StateUpdater) -> Unit){
        reducers.put(I::class.qualifiedName!!, ReducerHolder(reducer as Intent.(StateUpdater) -> Unit))
    }

    fun build(): MutableMap<String, ReducerHolder<Intent>> = reducers
}

class ReducerHolder<I: Intent>(
    val reducer: I.(StateUpdater) -> Unit
)