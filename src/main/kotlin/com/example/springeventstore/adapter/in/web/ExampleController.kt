package com.example.springeventstore.adapter.`in`.web

import com.example.springeventstore.application.port.`in`.CreateExampleUseCase
import com.example.springeventstore.application.port.`in`.UpdateExampleUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/examples")
class ExampleController(
    private val createExampleUseCase: CreateExampleUseCase,
    private val updateExampleUseCase: UpdateExampleUseCase,
) {
    @PostMapping
    fun create(
        @RequestBody request: CreateExampleRequest,
    ): ResponseEntity<CreateExampleResponse> {
        val command = request.toCommand()
        val id = createExampleUseCase.execute(command)
        return ResponseEntity.ok(CreateExampleResponse(id))
    }

    @PatchMapping
    fun update(
        @RequestBody request: UpdateExampleRequest,
    ): ResponseEntity<Unit> {
        val command = request.toCommand()
        updateExampleUseCase.execute(command)
        return ResponseEntity.ok().build()
    }
}
