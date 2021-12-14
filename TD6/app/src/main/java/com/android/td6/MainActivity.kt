package com.android.td6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var enteredRepoName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnValider : Button = findViewById(R.id.btn_val)
        val nomRepo : EditText = findViewById(R.id.et_message)

        btnValider.setOnClickListener{

            enteredRepoName= nomRepo.text.toString()
            val intent = Intent(this, AffichageActivity::class.java)
            intent.putExtra("nomRepo", enteredRepoName)
            startActivity(intent)
        }

        val service = Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)



        service.listRepos("adrienbusin")?.enqueue(object: Callback<List<Repo?>?>{
            override fun onResponse(call: Call<List<Repo?>?>, response: Response<List<Repo?>?>) {
                afficherRepos(response.body())
            }

            override fun onFailure(call: Call<List<Repo?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun afficherRepos(repos: List<Repo?>?) {
        Toast.makeText(this,"nombre de dépots : "+ repos!!.size, Toast.LENGTH_SHORT).show();
    }

}