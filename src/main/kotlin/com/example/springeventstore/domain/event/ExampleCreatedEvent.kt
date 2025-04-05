package com.example.springeventstore.domain.event

import com.example.springeventstore.domain.DomainEvent
import com.example.springeventstore.domain.entity.Example

data class ExampleCreatedEvent(
    override val aggregateId: Long,
) : DomainEvent() {
    companion object {
        fun from(example: Example) =
            ExampleCreatedEvent(
                aggregateId = example.id,
            )
    }
}
