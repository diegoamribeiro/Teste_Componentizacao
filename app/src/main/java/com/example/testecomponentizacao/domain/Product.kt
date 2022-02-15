package com.example.testecomponentizacao.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int? = null,
    val title: String,
    val price: String,
    val rating: Double,
    val reviews: Double,
    val imageUrl: Int
) : Parcelable