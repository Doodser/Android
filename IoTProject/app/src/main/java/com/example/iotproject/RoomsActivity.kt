package com.example.iotproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RoomsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        val r1Button: Button = findViewById(R.id.r1)
        r1Button.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomId", "r1")
            startActivity(intent)
        }

        val r2Button: Button = findViewById(R.id.r2)
        r2Button.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomId", "r2")
            startActivity(intent)
        }

        val r3Button: Button = findViewById(R.id.r3)
        r3Button.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomId", "r3")
            startActivity(intent)
        }

        val r4Button: Button = findViewById(R.id.r4)
        r4Button.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomId", "r4")
            startActivity(intent)
        }

        val r5Button: Button = findViewById(R.id.r5)
        r5Button.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomId", "r5")
            startActivity(intent)
        }

        val r6Button: Button = findViewById(R.id.r6)
        r6Button.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomId", "r6")
            startActivity(intent)
        }

        val r7Button: Button = findViewById(R.id.r7)
        r7Button.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomId", "r7")
            startActivity(intent)
        }

        val r8Button: Button = findViewById(R.id.r8)
        r8Button.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomId", "r8")
            startActivity(intent)
        }

        val exitButton: Button = findViewById(R.id.exitRoomsButton)
        exitButton.setOnClickListener {
            finish()
        }
    }
}