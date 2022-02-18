package com.example.testecomponentizacao.view.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testecomponentizacao.R
import com.example.testecomponentizacao.databinding.FragmentListBinding
import com.example.testecomponentizacao.model.Product
import com.example.testecomponentizacao.view.adapter.ProductListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        setHasOptionsMenu(true)
//        val mGson = Gson().toJson(loadElements())
//        Log.d("result", mGson)
        return view
    }

    private fun loadElements(): List<Product> {
        return listOf(
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria"),
            Product("Headset Bluetooth", "20,50", 4.6, 86, "https://imgur.com/5bfqR18", "16 horas", "Semi aviva", "Bluetooth 4.1", "Bluetooth", "18,4", "Bateria")
        )
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