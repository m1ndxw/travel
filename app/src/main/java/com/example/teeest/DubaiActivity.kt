package com.example.teeest

import android.os.Bundle
import android.widget.ImageView
import android.content.Intent
import android.widget.LinearLayout

import androidx.appcompat.app.AppCompatActivity

class DubaiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dubai)

        val logoDubai: ImageView = findViewById(R.id.logo_dubai)

        logoDubai.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val goTour: LinearLayout = findViewById(R.id.go_tour)
        goTour.setOnClickListener {
            val intent = Intent(this, TourActivity::class.java)
            intent.putExtra("SELECTED_CITY", "Дубай")
            startActivity(intent)
        }
    }
}
