package com.android.td5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactActivity : AppCompatActivity() {

    var contacts : ArrayList<Contact> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val rvContact : RecyclerView = findViewById(R.id.rvContacts)

        contacts.add(Contact("Gauweiler","Vincent","https://fr.wikipedia.org/wiki/Azathoth#/media/Fichier:Azathoth.jpg"))
        contacts.add(Contact("Charpentier","Valentin","https://fr.wikipedia.org/wiki/H._P._Lovecraft#/media/Fichier:H._P._Lovecraft,_June_1934.jpg"))
        contacts.add(Contact("Thomas","Bailleux","https://upload.wikimedia.org/wikipedia/commons/4/40/Gustave_Dor%C3%A9_-_Miguel_de_Cervantes_-_Don_Quixote_-_Part_1_-_Chapter_1_-_Plate_1_%22A_world_of_disorderly_notions%2C_picked_out_of_his_books%2C_crowded_into_his_imagination%22.jpg"))

        val adapter : ContactAdapter = ContactAdapter(contacts, this)

        rvContact.adapter = adapter

        rvContact.layoutManager = LinearLayoutManager(this)

    }
}