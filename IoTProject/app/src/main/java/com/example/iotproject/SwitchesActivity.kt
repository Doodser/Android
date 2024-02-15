package com.example.iotproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.serialization.json.Json

class SwitchesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switches)

        val recyclerView: RecyclerView = findViewById(R.id.settingsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val queue = Volley.newRequestQueue(this)
        val url = "http://84.237.51.142:5000/light_control/switches/states"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {response ->
                val switchesStates = Json.decodeFromString<Map<String, Boolean>>(response)
                val switches = mutableListOf<SwitchCompat>()
                for (s in switchesStates) {
                    val switch = SwitchCompat(this)
                    switch.text = s.key
                    switch.isChecked = s.value

                    switches.add(switch)
                }
                recyclerView.adapter = SwitchesRecyclerAdapter(switches)
            },
            {e ->
                e.printStackTrace()
            }
        )

        queue.add(stringRequest)
    }

    override fun onResume() {
        val closeButton: Button = findViewById(R.id.closeSwitchesRecycleViewButton)
        closeButton.setOnClickListener {
            finish()
        }

        super.onResume()
    }
}