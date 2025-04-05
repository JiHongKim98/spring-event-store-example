package com.example.springeventstore.adapter.out.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "examples")
class ExampleJpaEntity(
    @Column(nullable = false)
    val name: String,
    @Id
    val id: Long,
)
