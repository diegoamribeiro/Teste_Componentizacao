package com.example.testecomponentizacao.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testecomponentizacao.R

fun hideKeyboard(activity: Activity){
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusView = activity.currentFocus
    currentFocusView.let {
        inputMethodManager.hideSoftInputFromWindow(
            currentFocusView?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun ImageView.loadImage(imageUrl: String){
    Glide.with(this)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}