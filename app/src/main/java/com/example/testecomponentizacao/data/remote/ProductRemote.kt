package com.example.testecomponentizacao.data.remote


import android.os.Parcelable
import com.example.testecomponentizacao.domain.model.Product
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductRemote(
    @SerializedName("autonomy")
    val autonomy: String,
    @SerializedName("capture")
    val capture: String,
    @SerializedName("compatibility")
    val compatibility: String,
    @SerializedName("connection")
    val connection: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("power")
    val power: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("reviews")
    val reviews: Int
): Parcelable


fun ProductRemote.toProduct() = Product (
    id = null,
    autonomy = autonomy,
    capture = capture,
    compatibility = compatibility,
    connection = connection,
    height = height,
    imageUrl = imageUrl,
    model = model,
    power = power,
    price = price,
    rating = rating,
    reviews = reviews
)

