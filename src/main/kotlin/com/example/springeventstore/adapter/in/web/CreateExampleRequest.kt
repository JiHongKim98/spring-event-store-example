package com.example.springeventstore.adapter.`in`.web

import com.example.springeventstore.application.port.`in`.CreateExampleUseCase

data class CreateExampleRequest(
    val name: String,
) {
    fun toCommand(): CreateExampleUseCase.Command =
        CreateExampleUseCase.Command(
            name = name,
        )
}
