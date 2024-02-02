package com.example.scrolling

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settings: SharedPreferences = getSharedPreferences("ScrollingAppPreferences", MODE_PRIVATE)

        val text: TextView = findViewById(R.id.wordInMain)
        text.text = settings.getString("text", "Text")

        val setting1: TextView = findViewById(R.id.setting1Text)
        setting1.text = getString(R.string.setting_1_text) + settings.getBoolean("setting1", false).toString()

        val setting2: TextView = findViewById(R.id.setting2Text)
        setting2.text = getString(R.string.setting_2_text) + settings.getBoolean("setting2", false).toString()

        val setting3: TextView = findViewById(R.id.setting3Text)
        setting3.text = getString(R.string.setting_3_text) + settings.getBoolean("setting3", false).toString()

        val setting4: TextView = findViewById(R.id.setting4Text)
        setting4.text = getString(R.string.setting_4_text) + settings.getBoolean("setting4", false).toString()

        val setting5: TextView = findViewById(R.id.setting5Text)
        setting5.text = getString(R.string.setting_5_text) + settings.getBoolean("setting5", false).toString()

    }

    override fun onStart() {
        val settings: SharedPreferences = getSharedPreferences("ScrollingAppPreferences", MODE_PRIVATE)

        val text: TextView = findViewById(R.id.wordInMain)
        text.text = settings.getString("text", "Text")
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

        super.onResume()
    }
}