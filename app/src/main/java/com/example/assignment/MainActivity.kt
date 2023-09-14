package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<ImageView>(R.id.profileICON)
        btn.setOnClickListener {
            val intent = Intent(this,USERPROFILE::class.java)
            startActivity(intent)
        }
    }
}