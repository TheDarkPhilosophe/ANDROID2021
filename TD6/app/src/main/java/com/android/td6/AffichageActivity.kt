package com.android.td6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AffichageActivity : AppCompatActivity() {

    var repos : ArrayList<Repo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affichage)

        val rvRepo : RecyclerView = findViewById(R.id.rv_repo)
        val adapter : Adapter = Adapter(repos, this)

        val nomRepo : String? = intent.getStringExtra("nomRepo")

        val service = Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

        service.searchRepos(nomRepo).enqueue(object: Callback<Repos> {
            override fun onResponse(call: Call<Repos>, response: Response<Repos>) {
                val reposList = response.body()
                repos.clear()

                if (reposList != null) {
                    repos.addAll(reposList.items)
                    rvRepo.adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Repos>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        rvRepo.adapter = adapter
        rvRepo.layoutManager = LinearLayoutManager(this)

    }

}