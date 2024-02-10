package com.example.httptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


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

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.github.com/events"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val json = Json {ignoreUnknownKeys = true}
                val recyclerView: RecyclerView = findViewById(R.id.eventsRecycler)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = EventRecyclerAdapter(json.decodeFromString(response.toString()))
            },
            { e ->
                e.printStackTrace()
            })

        queue.add(stringRequest)



    }
}