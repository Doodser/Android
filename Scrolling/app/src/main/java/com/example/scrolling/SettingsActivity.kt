package com.example.scrolling

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val recyclerView: RecyclerView = findViewById(R.id.settingsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SettingsRecyclerAdapter(fillList())
    }

    override fun onResume() {
        val closeButton: Button = findViewById(R.id.closeSettingsRecycleViewButton)
        closeButton.setOnClickListener {
            finish()
        }

        super.onResume()
    }

    private fun fillList(): List<SwitchCompat> {
        val data = mutableListOf<SwitchCompat>()

        val settings: SharedPreferences = getSharedPreferences("ScrollingAppPreferences", MODE_PRIVATE)
        for(i in 1..30) {
            val switch: SwitchCompat = SwitchCompat(this)
            switch.text = "Setting $i"
            switch.isChecked = settings.getBoolean("Setting $i", false)
            data.add(switch)
        }

        return data
    }
}