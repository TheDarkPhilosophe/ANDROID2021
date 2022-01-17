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

class MainActivity : AppCompatActivity() {
    private val liste: ArrayList<Movie> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  upcoming_btn =findViewById<Button>(R.id.upcoming_btn)
        upcoming_btn.setOnClickListener(){
            val intent = Intent(this, UpcomingMovies::class.java)
            startActivity(intent)
        }


        val recycler = findViewById<RecyclerView>(R.id.RecyclerPoster)
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
        service.getPopularMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                title="Popular Movies"
                liste.addAll(it.results)
                recycler.adapter?.notifyDataSetChanged()
            }, { Log.e("erreur api", it.toString()) })

    }
}