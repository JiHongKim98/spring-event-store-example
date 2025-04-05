package com.example.springeventstore.domain.event

import com.example.springeventstore.domain.DomainEvent
import com.example.springeventstore.domain.entity.Example
import com.example.springeventstore.domain.vo.Name

data class ExampleUpdatedEvent(
    override val aggregateId: Long,
    val name: Name,
) : DomainEvent() {
    companion object {
        fun from(exampleEvent: Example): ExampleUpdatedEvent =
            ExampleUpdatedEvent(
                aggregateId = exampleEvent.id,
                name = exampleEvent.name,
            )
    }
}
