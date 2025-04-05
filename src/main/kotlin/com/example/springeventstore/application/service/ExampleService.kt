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
