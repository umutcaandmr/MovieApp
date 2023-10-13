package com.umutdemir.movieapp.util

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umutdemir.movieapp.R

fun ImageView.downloadFromUrl(url : String, placeholder : CircularProgressDrawable?){
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    val BASE_URL = "https://image.tmdb.org/t/p/w500/"
    println(BASE_URL + url)
    Glide.with(context).setDefaultRequestOptions(options).load(BASE_URL + url).into(this)
}
fun placeHolder(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 2f
        centerRadius = 12f
        setColorSchemeColors(Color.BLUE)
        start()
    }
}