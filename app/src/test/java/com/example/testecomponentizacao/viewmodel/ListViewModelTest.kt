package com.example.testecomponentizacao.viewmodel

import com.example.testecomponentizacao.repo.FakeProductRepository
import org.junit.Before


class ListViewModelTest {

    private lateinit var viewModel: ListViewModel

    @Before
    fun setup(){
        //Test Doubles
        //viewModel = ListViewModel(FakeProductRepository())

        //viewModel = ListViewModel()
    }

}