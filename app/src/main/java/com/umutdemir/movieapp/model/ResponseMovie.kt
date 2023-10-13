package com.umutdemir.movieapp.model

data class ResponseMovie(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)