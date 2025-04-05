package com.example.springeventstore.domain.vo

@JvmInline
value class Name(
    val value: String,
) {
    init {
        require(value.isNotBlank())
    }
}
