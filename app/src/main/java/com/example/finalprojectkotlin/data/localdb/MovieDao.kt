package com.example.finalprojectkotlin.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.finalprojectkotlin.data.model.Movie
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(item:Movie)

    @Delete
    fun deleteMovie(item:Movie)

    @Update
    fun updateMovie(item: Movie)

    @Query("SELECT * FROM movies ORDER BY content ASC")
    fun getMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE id =:id")
    fun getMovie(id:Int) : Movie

    @Query("DELETE FROM movies")
    fun deleteAll()
}