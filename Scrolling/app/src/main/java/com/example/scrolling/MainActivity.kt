package com.example.scrolling

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settings: SharedPreferences = getSharedPreferences("ScrollingAppPreferences", MODE_PRIVATE)

        val text: TextView = findViewById(R.id.wordInMain)
        text.text = settings.getString("text", "Text")

        printSettings()

        val image: ImageView = findViewById(R.id.mainImageView)
        image.setImageResource(when(settings.getInt("image", 0) + 1) {
            1 -> R.drawable.cat1
            2 -> R.drawable.cat2
            3 -> R.drawable.cat3
            4 -> R.drawable.cat4
            5 -> R.drawable.cat5
            6 -> R.drawable.cat6
            7 -> R.drawable.cat7
            8 -> R.drawable.cat8
            9 -> R.drawable.cat9
            10 -> R.drawable.cat10
            11 -> R.drawable.cat11
            12 -> R.drawable.cat12
            13 -> R.drawable.cat13
            14 -> R.drawable.cat14
            15 -> R.drawable.cat15
            else -> R.drawable.cat1
        })

    }

    override fun onStart() {
        val settings: SharedPreferences = getSharedPreferences("ScrollingAppPreferences", MODE_PRIVATE)

        val text: TextView = findViewById(R.id.wordInMain)
        text.text = settings.getString("text", "Text")

        printSettings()

        val image: ImageView = findViewById(R.id.mainImageView)

        image.setImageResource(when(settings.getInt("image", 0) + 1) {
            1 -> R.drawable.cat1
            2 -> R.drawable.cat2
            3 -> R.drawable.cat3
            4 -> R.drawable.cat4
            5 -> R.drawable.cat5
            6 -> R.drawable.cat6
            7 -> R.drawable.cat7
            8 -> R.drawable.cat8
            9 -> R.drawable.cat9
            10 -> R.drawable.cat10
            11 -> R.drawable.cat11
            12 -> R.drawable.cat12
            13 -> R.drawable.cat13
            14 -> R.drawable.cat14
            15 -> R.drawable.cat15
            else -> R.drawable.cat1
        })

        super.onStart()
    }

    override fun onResume() {
        val openRecyclerButton: Button = findViewById(R.id.openRecyclerView)
        openRecyclerButton.setOnClickListener {
            val intent = Intent(this, TextActivity::class.java)
            startActivity(intent)
        }

        val openSettingsRecyclerButton: Button = findViewById(R.id.settingsButton)
        openSettingsRecyclerButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val openImageRecyclerButton: Button = findViewById(R.id.imgButton)
        openImageRecyclerButton.setOnClickListener {
            val intent = Intent(this, ImageActivity::class.java)
            startActivity(intent)
        }

        super.onResume()
    }

    private fun printSettings() {
        val settings: SharedPreferences = getSharedPreferences("ScrollingAppPreferences", MODE_PRIVATE)

        val settingsText: TextView = findViewById(R.id.settingsText)
        val stringBuilder: StringBuilder = java.lang.StringBuilder()
        stringBuilder.append(getString(R.string.settings_text))
        for(i in 1..30) {
            stringBuilder.append("Setting $i:")
            stringBuilder.appendLine(settings.getBoolean("Setting $i", false))
        }
        settingsText.text = stringBuilder.toString()
    }
}