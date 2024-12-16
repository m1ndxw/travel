package com.example.teeest

import android.content.Intent
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Найти элементы в макете
        val spinner: Spinner = findViewById(R.id.topic_spinner)
        val textBlock1: TextView = findViewById(R.id.text_block_1)
        val textBlock2: TextView = findViewById(R.id.text_block_2)
        val textBlock3: TextView = findViewById(R.id.text_block_3)
        val navigateButton: Button = findViewById(R.id.go_button)

        val items = listOf("Дубай", "Стамбул", "Байкал", "Мурманск")

// Адаптер для Spinner с белым текстом в обычном состоянии
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)  // Используем кастомный layout для выпадающего списка

// Устанавливаем стиль для текста в обычном состоянии
        val spinner2: Spinner = findViewById(R.id.topic_spinner)
        spinner.adapter = adapter
        spinner.setBackgroundResource(R.drawable.spinner_background) // Можно добавить кастомный фон для Spinner (если нужно)



        // Логика для текстовых блоков
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        textBlock1.text = "Дубай — город в ОАЭ."
                        textBlock2.text = "Популярный курорт."
                        textBlock3.text = "Температура зимой: +25°C."
                    }
                    1 -> {
                        textBlock1.text = "Стамбул — город в Турции."
                        textBlock2.text = "Известен своим Босфором."
                        textBlock3.text = "Температура весной: +15°C."
                    }
                    2 -> {
                        textBlock1.text = "Байкал — озеро в России."
                        textBlock2.text = "Самое глубокое озеро в мире."
                        textBlock3.text = "Температура летом: +18°C."
                    }
                    3 -> {
                        textBlock1.text = "Мурманск — город в России."
                        textBlock2.text = "Северное сияние."
                        textBlock3.text = "Температура зимой: -10°C."
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Ничего не делать
            }
        }

        // Логика для кнопки "Перейти"
        navigateButton.setOnClickListener {
            val selectedPosition = spinner.selectedItemPosition
            val intent = when (selectedPosition) {
                0 -> Intent(this, DubaiActivity::class.java)
                1 -> Intent(this, StambulActivity::class.java)
                2 -> Intent(this, BaikalActivity::class.java)
                3 -> Intent(this, MurmanskActivity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }

        val butttonCartDubai: LinearLayout = findViewById(R.id.cart_dubai)

        butttonCartDubai.setOnClickListener {
            val intent = Intent(this, DubaiActivity::class.java)
            startActivity(intent)
        }

        val butttonCartStambul: LinearLayout = findViewById(R.id.cart_stambul)

        butttonCartStambul.setOnClickListener {
            val intent = Intent(this, StambulActivity::class.java)
            startActivity(intent)
        }

        val butttonCartBaikal: LinearLayout = findViewById(R.id.cart_baikal)

        butttonCartBaikal.setOnClickListener {
            val intent = Intent(this, BaikalActivity::class.java)
            startActivity(intent)
        }

        val butttonCartMurmansk: LinearLayout = findViewById(R.id.cart_murmansk)

        butttonCartMurmansk.setOnClickListener {
            val intent = Intent(this, MurmanskActivity::class.java)
            startActivity(intent)
        }


    }
}
