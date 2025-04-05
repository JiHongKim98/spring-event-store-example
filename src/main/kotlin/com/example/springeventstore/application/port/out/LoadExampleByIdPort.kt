package com.example.springeventstore.application.port.out

import com.example.springeventstore.domain.entity.Example

fun interface LoadExampleByIdPort {
    fun execute(id: Long): Example
}
