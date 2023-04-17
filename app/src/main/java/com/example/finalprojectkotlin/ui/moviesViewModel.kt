package com.example.finalprojectkotlin.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalprojectkotlin.data.model.Movie
import com.example.finalprojectkotlin.data.repository.MovieRepository

class moviesViewModel(application: Application)  : AndroidViewModel(application){

    private val repository = MovieRepository(application)

    val movies : LiveData<List<Movie>>? = repository.getMovies()

    private val _chosenItem = MutableLiveData<Movie>()
    val chosenMovie : LiveData<Movie> get() = _chosenItem

    fun setMovie(item:Movie) {
        _chosenItem.value = item
    }

    fun addMovie(item: Movie) {
        repository.addMovie(item)
    }

    fun deleteMovie(item:Movie) {
        repository.deleteMovie(item)
    }

}