package com.example.testecomponentizacao.domain.repo


import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.test.mock.MockApplication
import android.test.mock.MockContext
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testecomponentizacao.data.database.LocalDataSource
import com.example.testecomponentizacao.data.remote.ProductRemote
import com.example.testecomponentizacao.data.remote.RemoteDataSource
import com.example.testecomponentizacao.data.remote.toProduct
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.utils.Utils

import com.google.common.truth.Truth
import dagger.hilt.android.qualifiers.ApplicationContext
import io.mockk.*
import io.mockk.impl.stub.MockKStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class ProductRepositoryTest {

    @get:Rule
    val instantTestRule = InstantTaskExecutorRule()

    @Mock
    private val local = mockk<LocalDataSource>()

    @Mock
    private val remote = mockk<RemoteDataSource>()

    @ApplicationContext
    @Mock
    private var context = mockk<Application>(relaxed = true)

    private lateinit var productRepository: ProductRepository

    @Before
    fun setup() {
        MockKAnnotations.init()
        productRepository = ProductRepositoryImpl(local = local, remote = remote, context = context)
        mockkObject(Utils)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `when getProducts is called Should fetch list of products from network`() = runTest {

        val mockedList = listOf(
            ProductRemote(
                autonomy = "16 horas",
                capture = "Semi aviva",
                compatibility = "Bluetooth 4.1",
                connection = "Bluetooth",
                height = "18,4",
                imageUrl = "https://i.imgur.com/5bfqR18.png",
                model = "Headset Bluetooth",
                power = "Bateria",
                price = "20,50",
                rating = 4.6,
                reviews = 86
            )
        )


        val mocked = mockedList.map { it.toProduct() }

        coEvery { local.readProducts() } answers { mockedList.map { it.toProduct() } }
        coEvery { local.insertProducts(any()) } answers { }
        coEvery { Utils.hasInternetConnection(context) } returns true
        coEvery { remote.getAllProducts() } returns mockedList

        val list = productRepository.getProducts()

        Truth.assertThat(list).isEqualTo(mocked)
    }

    @Test
    fun `when getProducts is called Should fetch list of products from database`() = runTest {
        val mockedList = listOf(
            Product(
                id = 1,
                autonomy = "16 horas",
                capture = "Semi aviva",
                compatibility = "Bluetooth 4.1",
                connection = "Bluetooth",
                height = "18,4",
                imageUrl = "https://i.imgur.com/5bfqR18.png",
                model = "Headset Bluetooth",
                power = "Bateria",
                price = "20,50",
                rating = 4.6,
                reviews = 86
            )
        )

        coEvery { local.readProducts() } returns mockedList
        coEvery { local.insertProducts(any()) } answers {}
        coEvery { Utils.hasInternetConnection(context) } returns false

        val list = productRepository.getProducts()
        Truth.assertThat(list).isEqualTo(mockedList)
        coVerify(exactly = 0) { local.insertProducts(any()) }
    }

//    @Test
//    fun `verify HttpException`() = runBlockingTest {
//        val exception = RuntimeException("Unit Test Exception")
//        coEvery { remote.getAllProducts() } throws exception
//        coEvery { local.insertProducts(any()) } answers{}
//        coEvery { Utils.hasInternetConnection(any()) } returns true
//
//        try {
//            productRepository.getProducts()
//        }catch (e: Exception){
//            Truth.assertThat(e).isInstanceOf(RemoteException.Generic::class.java)
//        }
//    }
}
