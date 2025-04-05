package com.example.springeventstore.application.port.out

import com.example.springeventstore.domain.entity.Example

fun interface SaveExamplePort {
    fun execute(example: Example)
}
