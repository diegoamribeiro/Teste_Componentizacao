package com.example.testecomponentizacao.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testecomponentizacao.databinding.ListItemBinding
import com.example.testecomponentizacao.domain.Product

class ProductListAdapter: RecyclerView.Adapter<ProductListViewHolder>(){

    private var productList = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class ProductListViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)