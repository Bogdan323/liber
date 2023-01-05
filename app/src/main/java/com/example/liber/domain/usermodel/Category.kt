package com.example.liber.domain.usermodel

sealed class Category(val category: String) {

    object PSGames : Category("psgames")
    object Cupboard : Category("cupboard")
    object Table : Category("table")
    object Accessory : Category("accessory")
    object Furniture : Category("furniture")

}