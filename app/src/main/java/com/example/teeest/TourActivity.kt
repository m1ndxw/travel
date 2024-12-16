package com.example.teeest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour)

        val logoDubai: ImageView = findViewById(R.id.logo_dubai)

        logoDubai.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Получение данных из Intent
        val selectedCity = intent.getStringExtra("SELECTED_CITY") ?: "Неизвестный город"

        // Установка названия города в TextView
        val cityNameTextView: TextView = findViewById(R.id.selected_city_name)
        cityNameTextView.text = selectedCity

        // Обработка нажатия на кнопку "Оформить тур"
        val confirmButton: Button = findViewById(R.id.confirm_tour_button)
        val userNote: EditText = findViewById(R.id.user_note)

        confirmButton.setOnClickListener {
            val note = userNote.text.toString()
            // Показать сообщение о подтверждении
            Toast.makeText(this, "Тур \"$selectedCity\" оформлен!\nВаша почта: $note", Toast.LENGTH_LONG).show()

            // Переход на главный экран
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
