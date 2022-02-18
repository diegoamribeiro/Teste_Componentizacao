package com.example.testecomponentizacao.view.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testecomponentizacao.R
import com.example.testecomponentizacao.data.remote.NetworkResponse
import com.example.testecomponentizacao.databinding.FragmentListBinding
import com.example.testecomponentizacao.model.Product
import com.example.testecomponentizacao.view.adapter.ProductListAdapter
import com.example.testecomponentizacao.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var recyclerView: RecyclerView
    private val listAdapter: ProductListAdapter by lazy { ProductListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        loadRecyclerView()
        requestApiData()
        customActionBar()
        setHasOptionsMenu(true)

        return view
    }

    private fun loadRecyclerView(){
        recyclerView = binding.fragmentListRecyclerview
        recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun requestApiData() {
        viewModel.getAllProduct()
        viewModel.productResponse.observe(viewLifecycleOwner) { response ->
            Log.d("***ListFragment", response.data.toString())
            when (response) {
                is NetworkResponse.Success -> {
                    response.data?.let {
                        listAdapter.setData(it)
                    }
                }
                is NetworkResponse.Error -> {
                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
                }
                is NetworkResponse.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun customActionBar(){
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.elevation = 0f
        actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(),
            R.color.bg_top_headset
        )))
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }
}