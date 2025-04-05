package com.example.springeventstore.application.service

import com.example.springeventstore.application.port.`in`.CreateExampleUseCase
import com.example.springeventstore.application.port.`in`.UpdateExampleUseCase
import com.example.springeventstore.application.port.out.LoadExampleByIdPort
import com.example.springeventstore.application.port.out.SaveExamplePort
import com.example.springeventstore.domain.entity.Example
import com.example.springeventstore.domain.vo.Name
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExampleService(
    private val saveExamplePort: SaveExamplePort,
    private val loadExampleByIdPort: LoadExampleByIdPort,
) : CreateExampleUseCase,
    UpdateExampleUseCase {
    /**
     * Creates a new [Example] aggregate with the given [command] and persists it.
     *
     * During the creation of the [Example] entity, an [ExampleCreatedEvent] is registered in the domain.
     * It is expected that this event will be published as a side-effect when [saveExamplePort.execute] is called.
     *
     * The event publishing mechanism assumes that the persistence layer (e.g., JPA repository)
     * triggers event dispatching after saving the entity, either via interceptor or AOP.
     */
    @Transactional
    override fun execute(command: CreateExampleUseCase.Command): Long {
        val name = Name(command.name)

        val example = Example.create(name)
        saveExamplePort.execute(example)

        return example.id
    }

    @Transactional
    override fun execute(command: UpdateExampleUseCase.Command) {
        val example = loadExampleByIdPort.execute(command.id)

        val name = Name(command.name)
        val updatedExample = example.update(name)
        saveExamplePort.execute(updatedExample)
    }
}
