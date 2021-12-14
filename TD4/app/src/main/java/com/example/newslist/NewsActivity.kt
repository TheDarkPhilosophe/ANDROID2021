package com.example.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newslist.R
import android.widget.TextView
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import com.example.newslist.LoginActivity
import com.example.newslist.DetailsActivity

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        title = localClassName
        val logout_Btn = findViewById<View>(R.id.Logout_button) as Button
        val detail_btn = findViewById<View>(R.id.details_button) as Button
        val google_btn = findViewById<View>(R.id.google_button) as Button
        val username = findViewById<View>(R.id.login_name) as TextView
        val intent = intent
        val login: String?
        if (intent.hasExtra("login")) {
            login = intent.getStringExtra("login")
            username.text = login
        }
        logout_Btn.setOnClickListener {
            val intent = Intent(this@NewsActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        detail_btn.setOnClickListener {
            val intent = Intent(this@NewsActivity, DetailsActivity::class.java)
            startActivity(intent)
        }
        google_btn.setOnClickListener {
            val url = "https://news.google.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}