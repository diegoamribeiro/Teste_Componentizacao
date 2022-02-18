package com.example.testecomponentizacao.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val model: String,
    val price: String,
    val rating: Double,
    val reviews: Int,
    val imageUrl: String,
    val autonomy: String,
    val capture: String,
    val compatibility: String,
    val connection: String,
    val height: String,
    val power: String,
) : Parcelable