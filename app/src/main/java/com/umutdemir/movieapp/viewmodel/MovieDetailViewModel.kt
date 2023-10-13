package com.umutdemir.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.umutdemir.movieapp.model.Movie
import com.umutdemir.movieapp.model.MovieDetail
import com.umutdemir.movieapp.service.MovieAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : BaseViewModel(application){

    var moveiDetail = MutableLiveData<MovieDetail>()
    var movieLoading = MutableLiveData<Boolean>()
    var movieError = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val movieAPIService = MovieAPIService()

    fun getDataFromAPI(movieId : Int){
        movieLoading.value = true

        disposable.add(
            movieAPIService.getDetails(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieDetail>(){
                override fun onSuccess(t: MovieDetail) {
                    movieLoading.value = false
                    moveiDetail.value = t
                }

                override fun onError(e: Throwable) {
                    movieError.value = true
                    movieLoading.value = false
                }

            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}