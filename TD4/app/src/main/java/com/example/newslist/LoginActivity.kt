package com.example.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newslist.R
import android.widget.EditText
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.newslist.NewsActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = localClassName
        val news_Btn = findViewById<View>(R.id.login_button) as Button
        val username = findViewById<View>(R.id.username_edittext) as EditText
        news_Btn.setOnClickListener {
            val intent = Intent(this@LoginActivity, NewsActivity::class.java)
            intent.putExtra("login", username.text.toString())
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}