package com.example.springeventstore.application.handler

import com.example.springeventstore.domain.event.ExampleCreatedEvent
import com.example.springeventstore.domain.event.ExampleUpdatedEvent
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ExampleHandler {
    @TransactionalEventListener
    fun handleCreateEvents(event: ExampleCreatedEvent) {
        logger.info("receive create event | eventId :: ${event.eventId} | aggregateId :: ${event.aggregateId}")
    }

    @TransactionalEventListener
    fun handleUpdateEvents(event: ExampleUpdatedEvent) {
        logger.info("receive update event | eventId :: ${event.eventId} | aggregateId :: ${event.aggregateId}")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ExampleHandler::class.java)
    }
}
