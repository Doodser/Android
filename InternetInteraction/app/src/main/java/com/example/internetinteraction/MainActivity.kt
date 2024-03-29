package com.example.internetinteraction

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.eventsRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EventRecyclerAdapter(getEvents())

    }
    fun getEvents(): List<EventRecyclerAdapter.Event> {
        val events: List<EventRecyclerAdapter.Event> = mutableListOf()

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.github.com/events"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
               response.toString()
            },
            { e ->
               e.printStackTrace()
            })

        queue.add(stringRequest)

        return events
    }
}
