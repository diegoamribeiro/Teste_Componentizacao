package com.example.testecomponentizacao.view.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.testecomponentizacao.R
import com.example.testecomponentizacao.databinding.FragmentDetailsBinding
import com.example.testecomponentizacao.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        customActionBar()
        loadDetails()
        return binding.root
    }

    private fun loadDetails(){
        val currentProduct = args.currentProduct
        binding.apply {
            fragmentDetailsImgHeadset.loadImage(currentProduct.imageUrl, root)
            fragmentDetailsTxtModel.text = currentProduct.model
            fragmentDetailsTxtConnection.text = currentProduct.connection
            fragmentDetailsTxtCompatibility.text = currentProduct.compatibility
            fragmentDetailsTxtPower.text = currentProduct.power
            fragmentDetailsTxtAutonomy.text = currentProduct.autonomy
            fragmentDetailsTxtHeight.text = currentProduct.height
            fragmentDetailsTxtCapture.text = currentProduct.capture
        }
    }

    private fun customActionBar(){
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.elevation = 0f
        actionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(requireContext(),
            R.color.bg_top_headset
        ))
        )
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_left)
    }

}