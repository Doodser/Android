package com.example.iotproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchesButton: Button = findViewById(R.id.button)
        switchesButton.setOnClickListener {
            val intent = Intent(this, SwitchesActivity::class.java)
            startActivity(intent)
        }

        val roomsButton: Button = findViewById(R.id.roomsButton)
        roomsButton.setOnClickListener {
            val intent = Intent(this, RoomsActivity::class.java)
            startActivity(intent)
        }
    }
}