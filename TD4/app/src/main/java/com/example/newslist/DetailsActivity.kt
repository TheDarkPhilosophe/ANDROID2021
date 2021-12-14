package com.example.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newslist.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.newslist.NewsActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        title = localClassName
        val ok_Btn = findViewById<View>(R.id.ok_button) as Button
        ok_Btn.setOnClickListener {
            val intent = Intent(this@DetailsActivity, NewsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@DetailsActivity, NewsActivity::class.java)
        startActivity(intent)
    }
}