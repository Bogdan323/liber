package com.example.liber.domain.usermodel

sealed class Category(val category: String) {

    object PSGames : Category("psgames")

}