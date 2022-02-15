package com.example.testecomponentizacao

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testecomponentizacao.databinding.FragmentListBinding
import com.example.testecomponentizacao.domain.Product
import com.example.testecomponentizacao.view.adapter.ProductListAdapter


class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val listAdapter: ProductListAdapter by lazy { ProductListAdapter() }
    private lateinit var binding: FragmentListBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        recyclerView = binding.fragmentListRecyclerview
        recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        listAdapter.setData(loadElements())
        customActionBar()
        return view
    }

    private fun loadElements(): List<Product> {
        return listOf(
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini),
            Product(null, "Headset Bluetooth", "200,00", 45.6, 4.000, R.drawable.headset_mini)
        )
    }

    private fun customActionBar(){
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.elevation = 0f
        actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.bg_top_headset)))
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

}