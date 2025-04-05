package com.example.springeventstore.application.port.`in`

fun interface UpdateExampleUseCase {
    data class Command(
        val id: Long,
        val name: String,
    )

    fun execute(command: Command)
}
