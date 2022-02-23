package com.example.testecomponentizacao.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testecomponentizacao.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM PRODUCT_TABLE ORDER BY id ASC")
    fun readProduct() : Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT_TABLE WHERE model LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Product>>
}
