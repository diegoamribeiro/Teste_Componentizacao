package com.example.testecomponentizacao.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testecomponentizacao.utils.Constants.USER_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String?,
    val username: String,
    val password: String
): Parcelable