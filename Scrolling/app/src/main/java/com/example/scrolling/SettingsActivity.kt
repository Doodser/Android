package com.example.scrolling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val recyclerView: RecyclerView = findViewById(R.id.settingsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomSettingsRecyclerAdapter(fillList())

        val closeButton: Button = findViewById(R.id.closeSettingsRecycleViewButton)
        closeButton.setOnClickListener {
            finish()
        }
    }



    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (1..5).forEach { i -> data.add("Setting $i") }

        return data
    }
}