package com.example.testecomponentizacao.domain

data class Product(
    val id: Int?,
    val title: String,
    val price: String,
    val rating: Int,
    val reviews: Double,
    val imageUrl: String
)