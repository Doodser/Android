package com.example.internetinteraction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val recyclerView: RecyclerView = findViewById(R.id.eventsRecycler)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = EventRecyclerAdapter(getEvents())

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://api.github.com/events")
                .get()
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }
            }
        }

    }

//    fun getEvents(): List<JSONObject> {
//
//
//
//        return
//    }
}