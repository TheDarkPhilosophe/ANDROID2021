package com.example.tpmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tpmovies.adapters.RecyclerGenreAdapter
import com.example.tpmovies.model.Genre
import com.example.tpmovies.service.PopularMovies
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
        val release=findViewById<TextView>(R.id.releaseDate)
        val liste_genres : ArrayList<Genre> =ArrayList()
        val genre_rec =findViewById<RecyclerView>(R.id.RecyclerGenre)
        genre_rec.adapter=RecyclerGenreAdapter(liste_genres)
        genre_rec.layoutManager=LinearLayoutManager(this)
        val id = intent.extras?.getInt("movieId")

        val  upcoming_btn =findViewById<Button>(R.id.upcoming_btn)
        upcoming_btn.setOnClickListener(){
            val intent = Intent(this, UpcomingMovies::class.java)
            startActivity(intent)
        }

        val  popular_btn =findViewById<Button>(R.id.popular_btn)
        popular_btn.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



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
                release.text=it.releaseDate
                liste_genres.addAll(it.genres)
                genre_rec.adapter?.notifyDataSetChanged()

                title = it.title
                Glide.with(this)
                    .load(PopularMovies.IMAGE_URL + it.posterPath)
                    .centerCrop()
                    .into(poster)


            }, { Log.e("erreur api", it.toString()) })


    }
}