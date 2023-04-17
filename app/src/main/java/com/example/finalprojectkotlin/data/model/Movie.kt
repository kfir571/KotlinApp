package com.example.finalprojectkotlin.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

//@Parcelize
//data class Movie(val title:String, val description:String,val genre:String,val year:String,val photo: String?) : Parcelable

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @ColumnInfo(name = "content")
    val title:String,

    @ColumnInfo(name = "content_desc")
    val description:String,

    @ColumnInfo(name = "genre")
    val genre:String,

    @ColumnInfo(name = "year")
    val year:String,

    @ColumnInfo(name = "image")
    val photo: String?) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
//object ItemManager {
//
//    val movies : MutableList<Movie> = mutableListOf()
//
//    fun add(item: Movie) {
//        movies.add(item)
//    }
//
//    fun remove(index:Int) {
//        movies.removeAt(index)
//    }
