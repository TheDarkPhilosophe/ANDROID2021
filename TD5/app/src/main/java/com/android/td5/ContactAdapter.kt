package com.android.td5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactAdapter(private val contacts : List<Contact>, val context : Context) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val firstNameTv = itemView.findViewById<TextView>(R.id.tv_prenom)
        val lastNameTv = itemView.findViewById<TextView>(R.id.tv_nom)
        val photoIv = itemView.findViewById<ImageView>(R.id.iv_contact)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context : Context = parent.context
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val contactView : View = inflater.inflate(R.layout.item_contact, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstNameTv.text = contacts[position].prenom
        holder.lastNameTv.text = contacts[position].nom
        Glide.with(context)
            .load(contacts[position].imageUrl)
            .into(holder.photoIv)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }


}