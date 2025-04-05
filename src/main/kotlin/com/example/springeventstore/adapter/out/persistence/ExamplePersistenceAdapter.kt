package com.example.springeventstore.adapter.out.persistence

import com.example.springeventstore.application.port.out.LoadExampleByIdPort
import com.example.springeventstore.application.port.out.SaveExamplePort
import com.example.springeventstore.domain.entity.Example
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ExamplePersistenceAdapter(
    private val exampleJpaRepository: ExampleJpaRepository,
) : SaveExamplePort,
    LoadExampleByIdPort {
    override fun execute(example: Example) {
        val exampleJpaEntity = ExamplePersistenceMapper.toJpaEntity(example)
        exampleJpaRepository.save(exampleJpaEntity)
    }

    override fun execute(id: Long): Example {
        val exampleJpaEntity = exampleJpaRepository.findByIdOrNull(id)
            ?: throw RuntimeException("NOT FOUND")
        return ExamplePersistenceMapper.toDomainEntity(exampleJpaEntity)
    }
}
