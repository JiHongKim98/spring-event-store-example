package com.example.springeventstore.adapter.`in`.web

import com.example.springeventstore.application.port.`in`.UpdateExampleUseCase

data class UpdateExampleRequest(
    val id: Long,
    val name: String,
) {
    fun toCommand(): UpdateExampleUseCase.Command =
        UpdateExampleUseCase.Command(
            id = id,
            name = name,
        )
}
