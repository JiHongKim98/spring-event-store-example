package com.example.springeventstore.domain.entity

import com.example.springeventstore.domain.AggregateRoot
import com.example.springeventstore.domain.event.ExampleCreatedEvent
import com.example.springeventstore.domain.event.ExampleUpdatedEvent
import com.example.springeventstore.domain.vo.Name

data class Example(
    override val id: Long,
    val name: Name,
) : AggregateRoot() {
    /**
     * update name and register updated event in event context
     *
     * @see com.example.springeventstore.support.context.EventContext
     * @author JiHongKim98
     * @return ExampleEntity
     */
    fun update(name: Name): Example {
        val updatedExample = copy(name = name)

        registerEvent(ExampleUpdatedEvent.from(updatedExample))

        return updatedExample
    }

    companion object {
        /**
         * create new domain entity and register created event in event context
         *
         * @see com.example.springeventstore.support.context.EventContext
         * @author JiHongKim98
         * @return ExampleEntity
         */
        fun create(name: Name) =
            Example(
                id = generateId(),
                name = name,
            ).also {
                // create with creation events
                it.registerEvent(ExampleCreatedEvent.from(it))
            }
    }
}
