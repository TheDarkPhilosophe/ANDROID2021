package com.example.tp_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_android.R
import com.example.tp_android.model.Movie
import com.example.tp_android.service.PopularMovies

class RecyclerAdapter(private val liste : List<Movie>, val context: Context, private val listener: (Movie) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val photo: ImageView = itemView.findViewById(R.id.Poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context : Context = parent.context
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.items_rv, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(PopularMovies.IMAGE_URL + liste[position].posterPath)
            .into(holder.photo)
        holder.itemView.setOnClickListener{
            listener(liste[position])
        }
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}