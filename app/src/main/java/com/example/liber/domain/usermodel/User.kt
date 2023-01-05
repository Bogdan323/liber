package com.example.liber.domain.usermodel

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val imagePath: String = ""
) {
    constructor(): this("","","","")
}
