package com.example.movieroom2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val imageView : ImageView = findViewById(R.id.imgcreate)
        imageView.setOnClickListener { viewcreate()}
        val imageView2 : ImageView = findViewById(R.id.imgtasklist)
        imageView2.setOnClickListener { viewtasklist()}
    }
    private fun viewcreate() {
        val intent = Intent(this, Insert::class.java)
        startActivity(intent)
    }
    private fun viewtasklist() {
        val intent = Intent(this, Show::class.java)
        startActivity(intent)
    }
}