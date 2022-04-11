package com.example.testecomponentizacao.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testecomponentizacao.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<Product>)

    @Query("SELECT * FROM PRODUCT_TABLE ORDER BY id ASC")
    suspend fun readProducts() : List<Product>

    @Query("DELETE FROM PRODUCT_TABLE")
    suspend fun deleteAllProducts()

}