package com.example.tpmovies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tpmovies.R
import com.example.tpmovies.model.Genre

class RecyclerGenreAdapter(private val liste : List<Genre>) : RecyclerView.Adapter<RecyclerGenreAdapter.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val genreName: TextView = itemView.findViewById(R.id.genres_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context : Context = parent.context
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.items_genre_rv, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      holder.genreName.text = liste[position].name
      //  Log.e("erreur api", liste[position].name)
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}