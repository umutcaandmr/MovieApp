package com.umutdemir.movieapp.service

import com.umutdemir.movieapp.model.Movie
import com.umutdemir.movieapp.model.MovieDetail
import com.umutdemir.movieapp.model.ResponseMovie
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPI {

    @GET("movie/popular?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
    fun getPopular() : Single<ResponseMovie>

    @GET("movie/top_rated?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
    fun getTopRated() : Single<ResponseMovie>

    @GET("movie/now_playing?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
     fun getNowPlaying() : Single<ResponseMovie>

    @GET("movie/upcoming?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
     fun getUpcoming() : Single<ResponseMovie>

    @GET("movie/{movie_id}?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
     fun getDetails(@Path("movie_id") id : Int) : Single<MovieDetail>

}