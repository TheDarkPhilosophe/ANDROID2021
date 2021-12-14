package com.android.td6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val repos: List<Repo>, val context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val nomRepo = itemView.findViewById<TextView>(R.id.tv_repo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context : Context = parent.context
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val repoView : View = inflater.inflate(R.layout.items_repos, parent, false)

        return ViewHolder(repoView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nomRepo.text = repos[position].name

    }

    override fun getItemCount(): Int {
        return repos.size
    }


}