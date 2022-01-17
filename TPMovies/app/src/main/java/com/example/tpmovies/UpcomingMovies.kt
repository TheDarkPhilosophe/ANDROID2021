package com.example.tpmovies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tpmovies.adapters.RecyclerAdapter
import com.example.tpmovies.model.Movie
import com.example.tpmovies.service.PopularMovies
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpcomingMovies : AppCompatActivity() {
    private val liste: ArrayList<Movie> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upcoming_movies)

        val  popular_btn =findViewById<Button>(R.id.popular_btn)
        popular_btn.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val recycler = findViewById<RecyclerView>(R.id.RecyclerUpcoming)
        recycler.adapter = RecyclerAdapter(liste, this)  { movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            startActivity(intent)
        }

        recycler.layoutManager = GridLayoutManager(this,2)

        val service = Retrofit.Builder()
            .baseUrl(PopularMovies.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PopularMovies::class.java)
        service.getUpcomingMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                title="Upcoming Movies"
                liste.addAll(it.results)
                recycler.adapter?.notifyDataSetChanged()
            }, { Log.e("erreur api", it.toString()) })

    }
}
