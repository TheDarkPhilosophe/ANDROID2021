package com.android.td6

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubService {
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repo?>>?

    @GET("/search/repositories")
    fun searchRepos(@Query("q") query: String?): Call<Repos>

    companion object {
        const val ENDPOINT = "https://api.github.com"
    }
}