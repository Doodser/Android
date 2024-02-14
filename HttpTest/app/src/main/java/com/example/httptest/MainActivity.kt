package com.example.httptest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.eventsRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.github.com/events"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val json = Json {ignoreUnknownKeys = true}
                recyclerView.adapter = EventRecyclerAdapter(json.decodeFromString(response.toString()))
            },
            { e ->
                e.printStackTrace()
            })

        queue.add(stringRequest)
    }
}