package com.example.testecomponentizacao.utils

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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

fun ImageView.loadImage(imageUrl: String, viewGroup: ViewGroup){
    Glide.with(viewGroup)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>){
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T) {
            removeObserver(this)
            observer.onChanged(t)
        }
    })
}