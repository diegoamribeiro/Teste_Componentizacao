package com.example.testecomponentizacao.view.fragments

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.testecomponentizacao.R
import com.example.testecomponentizacao.data.preferences.UserPreferencesRepository
import com.example.testecomponentizacao.data.remote.LiveDataInternetConnections
import com.example.testecomponentizacao.databinding.FragmentListBinding
import com.example.testecomponentizacao.view.LoginActivity
import com.example.testecomponentizacao.view.ResponseViewState
import com.example.testecomponentizacao.view.adapter.ProductListAdapter
import com.example.testecomponentizacao.viewmodel.ListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<ListViewModel>()
    private lateinit var recyclerView: RecyclerView
    private val listAdapter: ProductListAdapter by lazy { ProductListAdapter() }
    private lateinit var cld: LiveDataInternetConnections

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding.fragmentListShimmer.startShimmer()
        cld = LiveDataInternetConnections(requireActivity().application)

        loadRecyclerView()
        requestApiData()
        customActionBar()
        setHasOptionsMenu(true)
        logout()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        checkInternetConnection()
    }

    private fun logout() {
        val preferences = UserPreferencesRepository(requireContext())

        binding.fragmentListBtnLogout.setOnClickListener {
            val intentLogin = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intentLogin)
            lifecycleScope.launch {
                preferences.deleteAllData()
            }
            activity?.finish()
        }
    }

    private fun loadRecyclerView() {
        recyclerView =
            binding.fragmentListRecyclerview
        with(recyclerView) {
            adapter = listAdapter
        }
    }

    private fun requestApiData() {
        viewModel.getAllProducts()
        viewModel.productResponse.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ResponseViewState.Success<*> -> {
                    listAdapter.setData(viewState.data!!)
                }
                is ResponseViewState.Error -> {

                }
            }
        }
    }

    private fun customActionBar() {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.elevation = 0f
        actionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.bg_top_headset
                )
            )
        )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchThroughDatabase(newText)
        }

        binding.fragmentListShimmer.visibility = View.GONE

        return true
    }

    private fun searchThroughDatabase(query: String) {
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showSnackBar(message: String, color: Int, length: Int) {
        Snackbar.make(requireActivity().findViewById(R.id.container), message, length)
            .setAction("OK", null)
            .setBackgroundTint(color).show()
    }

    private fun checkInternetConnection() {
        cld.observe(this) { isConnected ->
            if (isConnected) {
                showSnackBar(
                    "Connected", ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    ), Snackbar.LENGTH_SHORT
                )
            } else {
                showSnackBar(
                    "No Connection! Data could be up dated",
                    ContextCompat.getColor(requireContext(), R.color.vermilion),
                    Snackbar.LENGTH_INDEFINITE
                )
            }
        }
    }

}