package com.umutdemir.movieapp.service

import com.umutdemir.movieapp.model.Movie
import com.umutdemir.movieapp.model.MovieDetail
import com.umutdemir.movieapp.model.ResponseMovie
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {

    private val BASE_URL = "https://api.themoviedb.org/3/"

    val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build().create(MovieAPI::class.java)


    fun getPopular(): Single<ResponseMovie>{
        return api.getPopular()
    }

    fun getNowPlaying(): Single<ResponseMovie>{
        return api.getNowPlaying()
    }

    fun getTopRated(): Single<ResponseMovie>{
        return api.getTopRated()
    }

    fun getUpcoming(): Single<ResponseMovie>{
        return api.getUpcoming()
    }

    fun getDetails(movieId:Int) : Single<MovieDetail>{
        return api.getDetails(movieId)
    }
}