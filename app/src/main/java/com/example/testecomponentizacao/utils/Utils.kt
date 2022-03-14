package com.example.testecomponentizacao.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.internal.applyConnectionSpec


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

@RequiresApi(Build.VERSION_CODES.M)
fun hasInternetConnection(context: Context): Boolean {
    val connectivityManager = Contexts.getApplication(context).getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    connectivityManager.addDefaultNetworkActiveListener {

    }
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when{
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> return false
    }
}