package com.example.internetinteraction

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val recyclerView: RecyclerView = findViewById(R.id.eventsRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EventRecyclerAdapter(getEvents())
    }
    fun getEvents(): List<EventRecyclerAdapter.Event> {
        val events: List<EventRecyclerAdapter.Event> = mutableListOf()

        val result = URL("https://api.github.com/events").readText()
        Json.decodeFromString<EventRecyclerAdapter.Event>(result)


        return events
    }
}