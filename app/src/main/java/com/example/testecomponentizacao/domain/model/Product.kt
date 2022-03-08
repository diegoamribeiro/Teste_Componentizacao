package com.example.testecomponentizacao.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testecomponentizacao.utils.Constants.PRODUCT_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = PRODUCT_TABLE)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
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