package com.example.finalprojectkotlin.data.repository

import android.app.Application
import com.example.finalprojectkotlin.data.model.Movie
import com.example.finalprojectkotlin.data.localdb.MovieDao
import com.example.finalprojectkotlin.data.localdb.MovieDataBase

class MovieRepository(application: Application) {
    private var movieDao:MovieDao?
    init {
        val db  = MovieDataBase.getDatabase(application.applicationContext)
        movieDao = db?.moviesDao()
    }

    fun getMovies() = movieDao?.getMovies()

    fun addMovie(item:Movie) {
        movieDao?.addMovie(item)
    }

    fun deleteMovie(item: Movie) {
        movieDao?.deleteMovie(item)
    }

    fun getMovie(id:Int)  = movieDao?.getMovie(id)

    fun deleteAll() {
        movieDao?.deleteAll()
    }

}