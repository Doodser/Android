package com.example.iotproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val roomId = intent.extras?.getString("roomId")

        val recyclerView: RecyclerView = findViewById(R.id.roomSwitches)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val queue = Volley.newRequestQueue(this)
        val url = "http://84.237.51.142:5000/light_control"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {response ->
                val jsonObjResponse = Json.decodeFromString<JsonObject>(response)

                val rooms: JsonArray = jsonObjResponse["rooms"] as JsonArray
                for(room in rooms) {
                    if (room.jsonObject["id"].toString().replace("\"", "") == roomId) {
                        val roomName: TextView = findViewById(R.id.roomName)
                        roomName.text =
                            room.jsonObject["name"].toString().replace("\"", "")
                    }
                }

                val switches = mutableListOf<SwitchCompat>()
                val switchesNames = mutableListOf<String>()

                val switchesJson: JsonArray = jsonObjResponse["switches"] as JsonArray
                for(switchJson in switchesJson) {
                    if(switchJson.jsonObject["room"].toString().replace("\"", "") == roomId) {
                        val switch = SwitchCompat(this)

                        switch.text =
                            switchJson.jsonObject["id"].toString().replace("\"", "")
                        switch.isChecked =
                            (switchJson.jsonObject["state"].toString() == "true")

                        switches.add(switch)
                        switchesNames.add(
                            switchJson.jsonObject["name"].toString().replace("\"", ""))
                    }
                }

                recyclerView.adapter = SwitchesRecyclerAdapter(switches, switchesNames)
            },
            {e ->
                e.printStackTrace()
            }
        )

        queue.add(stringRequest)

        val exitButton: Button = findViewById(R.id.exitRoomButton)
        exitButton.setOnClickListener {
            finish()
        }
    }
}