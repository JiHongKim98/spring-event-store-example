package com.example.springeventstore.application.port.`in`

fun interface CreateExampleUseCase {
    data class Command(
        val name: String,
    )

    fun execute(command: Command): Long
}
