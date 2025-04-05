package com.example.springeventstore.domain

import com.example.springeventstore.support.context.EventContext
import com.example.springeventstore.support.utils.TsidGenerator

abstract class AggregateRoot {
    abstract val id: Long

    protected fun registerEvent(event: DomainEvent) {
        EventContext.appendEvent(event)
    }

    companion object {
        @JvmStatic
        fun generateId() = TsidGenerator.generateIdToLong()
    }
}
