package com.example.scrolling

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        val recyclerView: RecyclerView = findViewById(R.id.textRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomItemsRecyclerAdapter(fillList())

        val closeButton: Button = findViewById(R.id.closeTextRecycleViewButton)
        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("item no.$i") }

        return data
    }

    fun clickOnItem(v: View) {
       if(v is TextView) {
           val itemText: TextView = v
           val settings: SharedPreferences = getSharedPreferences("ScrollingAppPreferences", MODE_PRIVATE)
           settings.edit().putString("text", itemText.text.toString()).apply()
           Toast.makeText(this, "Установлен текст: ${itemText.text}", Toast.LENGTH_SHORT).show()
       }

    }
}