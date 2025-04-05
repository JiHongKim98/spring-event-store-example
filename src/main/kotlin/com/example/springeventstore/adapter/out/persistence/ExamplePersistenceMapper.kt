package com.example.springeventstore.adapter.out.persistence

import com.example.springeventstore.domain.entity.Example
import com.example.springeventstore.domain.vo.Name

object ExamplePersistenceMapper {
    fun toDomainEntity(exampleJpaEntity: ExampleJpaEntity): Example =
        Example(
            id = exampleJpaEntity.id,
            name = Name(exampleJpaEntity.name),
        )

    fun toJpaEntity(example: Example): ExampleJpaEntity =
        ExampleJpaEntity(
            id = example.id,
            name = example.name.value,
        )
}
