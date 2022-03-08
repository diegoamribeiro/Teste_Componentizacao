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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.testecomponentizacao.R
import com.example.testecomponentizacao.databinding.FragmentListBinding
import com.example.testecomponentizacao.utils.observeOnce
import com.example.testecomponentizacao.view.adapter.ProductListAdapter
import com.example.testecomponentizacao.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<ListViewModel>()
    private lateinit var recyclerView: RecyclerView
    private val listAdapter: ProductListAdapter by lazy { ProductListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding.fragmentListShimmer.startShimmer()
        val view = binding.root

        //requestDatabase()

        loadRecyclerView()
        requestApiData()
        customActionBar()
        setHasOptionsMenu(true)
        return view
    }

    private fun loadRecyclerView(){
        recyclerView =
            binding.fragmentListRecyclerview
        with(recyclerView){
            adapter = listAdapter
        }
    }

    private fun requestDatabase(){
        viewModel.productResponse.observeOnce(viewLifecycleOwner){ database ->
            if (database.isNotEmpty()){
                Log.d("***requestDatabase", "Request Database")
                listAdapter.setData(database)
            }else{
                requestApiData()
            }
        }
    }

    private fun requestApiData() {
        viewModel.getAllProducts()
        Log.d("***requestAPI", "Request API")
        viewModel.productResponse.observe(viewLifecycleOwner) { listProducts ->
            if (listProducts.isNotEmpty()){
                listAdapter.setData(listProducts)
                binding.fragmentListShimmer.hideShimmer()
            }else{
                Toast.makeText(requireContext(), "Sem resposta", Toast.LENGTH_SHORT).show()
                binding.fragmentListShimmer.visibility = View.GONE
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
        if (query != null){
            searchThroughDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null){
            searchThroughDatabase(newText)
        }
        binding.fragmentListShimmer.visibility = View.GONE

        return true
    }

    private fun searchThroughDatabase(query: String){
        val searchQuery = "%$query%"
//        viewModel.searchFromDatabase(searchQuery).observe(this){ list ->
//            list?.let {
//                listAdapter.setData(it)
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }
}