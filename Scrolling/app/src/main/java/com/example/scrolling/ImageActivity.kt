package com.example.scrolling

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val recyclerView: RecyclerView = findViewById(R.id.imageRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ImageRecyclerAdapter(fillList())
    }

    override fun onResume() {
        val closeButton: Button = findViewById(R.id.closeImageRecycleViewButton)
        closeButton.setOnClickListener {
            finish()
        }

        super.onResume()
    }

    private fun fillList(): List<Int> {
        val data = mutableListOf<Int>()

        data.add(R.drawable.cat1)
        data.add(R.drawable.cat2)
        data.add(R.drawable.cat3)
        data.add(R.drawable.cat4)
        data.add(R.drawable.cat5)
        data.add(R.drawable.cat6)
        data.add(R.drawable.cat7)
        data.add(R.drawable.cat8)
        data.add(R.drawable.cat9)
        data.add(R.drawable.cat10)
        data.add(R.drawable.cat11)
        data.add(R.drawable.cat12)
        data.add(R.drawable.cat13)
        data.add(R.drawable.cat14)
        data.add(R.drawable.cat15)


        return data
    }
}