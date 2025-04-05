package com.example.springeventstore.support.context

object EventContext {
    private val eventStore: ThreadLocal<MutableList<Any>> = ThreadLocal.withInitial { mutableListOf() }

    fun appendEvent(event: Any) {
        eventStore.get().add(event)
    }

    fun raiseEvents(publisher: (Any) -> Unit) {
        eventStore.get().forEach { event ->
            publisher(event)
        }
        eventStore.remove()
    }

    fun clearEvents() {
        eventStore.remove()
    }
}
