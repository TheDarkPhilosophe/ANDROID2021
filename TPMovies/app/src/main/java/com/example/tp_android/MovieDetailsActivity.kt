package com.example.tp_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tp_android.service.PopularMovies
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val resume = findViewById<TextView>(R.id.resume)
        val poster = findViewById<ImageView>(R.id.imageBackground)
        //val release=findViewById<TextView>(R.releaseDate)

        val id = intent.extras?.getInt("movieId")

        val service = Retrofit.Builder()
            .baseUrl(PopularMovies.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PopularMovies::class.java)
        service.getMovieById(id!!).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                resume.text = it.overview

                title = it.title
                Glide.with(this)
                    .load(PopularMovies.IMAGE_URL + it.posterPath)
                    .centerCrop()
                    .into(poster)


            }, { Log.e("erreur api", it.toString()) })


    }
}