package com.example.springeventstore.domain

import java.util.UUID

abstract class DomainEvent {
    val eventId: String = UUID.randomUUID().toString()

    abstract val aggregateId: Long
}
