package com.example.tpmovies.service

import com.example.tpmovies.model.Movie
import com.example.tpmovies.model.Results
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface PopularMovies {
    companion object{
        val BASE_URL="https://api.themoviedb.org/3/"
        val IMAGE_URL="https://image.tmdb.org/t/p/w342"
        //val UNC_URL="https://api.themoviedb.org/3/movie/upcoming?api_key=b2fabf018f7e324e16197a01da3092b9&page=1"

    }
    @GET ("movie/popular?api_key=b2fabf018f7e324e16197a01da3092b9")
    fun getPopularMovies(): Observable<Results>

    @GET("movie/{id}?api_key=b2fabf018f7e324e16197a01da3092b9" )
    fun getMovieById(@Path("id") movieId: Int): Observable<Movie>

    @GET("movie/upcoming?api_key=b2fabf018f7e324e16197a01da3092b9&page=1")
    fun getUpcomingMovies():Observable<Results>
}