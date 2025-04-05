package com.example.springeventstore.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface ExampleJpaRepository : JpaRepository<ExampleJpaEntity, Long>
