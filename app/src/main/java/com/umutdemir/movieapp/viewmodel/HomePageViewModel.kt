package com.umutdemir.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.umutdemir.movieapp.model.ResponseMovie
import com.umutdemir.movieapp.service.MovieAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class HomePageViewModel(application: Application) : BaseViewModel(application) {

    var popularMovieList = MutableLiveData<ResponseMovie>()
    var popularMovieLoading = MutableLiveData<Boolean>()
    var popularMovieError = MutableLiveData<Boolean>()

    var nowPlayingMovieList = MutableLiveData<ResponseMovie>()
    var nowPlayingMovieLoading = MutableLiveData<Boolean>()
    var nowPlayingMovieError = MutableLiveData<Boolean>()

    var topRatedMovieList = MutableLiveData<ResponseMovie>()
    var topRatedMovieLoading = MutableLiveData<Boolean>()
    var topRatedMovieError = MutableLiveData<Boolean>()

    var upcomingMovieList = MutableLiveData<ResponseMovie>()
    var upcomingMovieLoading = MutableLiveData<Boolean>()
    var upcomingMovieError = MutableLiveData<Boolean>()

    private val movieAPIService = MovieAPIService()
    private val disposablePopular = CompositeDisposable()
    private val disposableTopRated = CompositeDisposable()
    private val disposableUpcoming = CompositeDisposable()
    private val disposableNowPlaying = CompositeDisposable()


    fun getPopularFromAPI() {
        popularMovieLoading.value = true

        disposablePopular.add(
            movieAPIService.getPopular().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<ResponseMovie>(){
                override fun onSuccess(t: ResponseMovie) {
                    popularMovieList.value = t
                    popularMovieLoading.value = false
                }

                override fun onError(e: Throwable) {
                    popularMovieError.value = true
                    popularMovieLoading.value = false
                }

            })
        )

    }

    fun getNowPlayingFromAPI() {
        nowPlayingMovieLoading.value = true

        disposableNowPlaying.add(
            movieAPIService.getNowPlaying().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<ResponseMovie>(){
                override fun onSuccess(t: ResponseMovie) {
                    nowPlayingMovieList.value = t
                    nowPlayingMovieLoading.value = false
                }

                override fun onError(e: Throwable) {
                    nowPlayingMovieError.value = true
                    nowPlayingMovieLoading.value = false
                }

            })
        )

    }

    fun getTopRatedFromAPI() {
        topRatedMovieLoading.value = true

        disposableTopRated.add(
            movieAPIService.getTopRated().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<ResponseMovie>(){
                override fun onSuccess(t: ResponseMovie) {
                    topRatedMovieList.value = t
                    topRatedMovieLoading.value = false
                }

                override fun onError(e: Throwable) {
                    topRatedMovieError.value = true
                    topRatedMovieLoading.value = false
                }

            })
        )

    }

    fun getUpcomingFromAPI() {
        upcomingMovieLoading.value = true

        disposableUpcoming.add(
            movieAPIService.getUpcoming().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<ResponseMovie>(){
                override fun onSuccess(t: ResponseMovie) {
                    upcomingMovieList.value = t
                    upcomingMovieLoading.value = false
                }

                override fun onError(e: Throwable) {
                    upcomingMovieError.value = true
                    upcomingMovieLoading.value = false
                }

            })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposablePopular.clear()
        disposableUpcoming.clear()
        disposableTopRated.clear()
        disposableNowPlaying.clear()
    }


}