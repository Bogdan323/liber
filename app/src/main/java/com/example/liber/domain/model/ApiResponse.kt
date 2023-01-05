package com.example.liber.domain.model

data class ApiResponse(
    val next: String?,
    val previous: String?,
    val games: List<Game>
)