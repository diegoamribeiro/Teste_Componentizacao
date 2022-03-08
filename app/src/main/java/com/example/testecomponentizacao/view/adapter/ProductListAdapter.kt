package com.example.testecomponentizacao.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testecomponentizacao.databinding.ListItemBinding
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.utils.DiffUtilGeneric
import com.example.testecomponentizacao.utils.loadImage
import com.example.testecomponentizacao.view.fragments.ListFragmentDirections


class ProductListAdapter : RecyclerView.Adapter<ProductListViewHolder>() {

    private var productList = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.binding.apply {
            fragmentListTxtTitle.text = productList[position].model
            fragmentListTxtPrice.text = productList[position].price
            fragmentListTxtRating.text = productList[position].rating.toString()
            fragmentListTxtReviews.text = productList[position].reviews.toString()
            fragmentListImgHeadsetMini.loadImage(productList[position].imageUrl, root)

            holder.itemView.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailsFragment(productList[position])
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount() = productList.size

    fun setData(list: List<Product>) {
        val productDiffUtil = DiffUtilGeneric(productList, list)
        val productResult = DiffUtil.calculateDiff(productDiffUtil)
        this.productList = list
        productResult.dispatchUpdatesTo(this)
    }
}

class ProductListViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)