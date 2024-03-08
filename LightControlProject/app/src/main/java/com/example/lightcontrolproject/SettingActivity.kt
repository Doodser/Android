package com.example.lightcontrolproject

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.CorrectionInfo
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.serialization.json.Json

class SettingActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        SetDataWithRoomsToRecyclerView()
    }

    fun ExitButton(v: View?)
    {
        finish()
    }

    fun SetDataWithRoomsToRecyclerView()
    {
        //val roomName : TextView = findViewById(R.id.roomName)
        //val shared: SharedPreferences = this.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)
        //val name : String? = shared.getString("room", "r0")
        //roomName.text = name

        val recyclerView : RecyclerView = findViewById(R.id.settingRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val shared: SharedPreferences = this.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)
        val coordinates = mutableListOf<SettingAdapter.CoordinateHolder>()
        val rooms = mutableListOf<RoomObj>()
        val switches = mutableListOf<Int>()

        val queue = Volley.newRequestQueue(this)
        val url = "http://84.237.51.142:5000/light_control"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                //val json = Json {ignoreUnknownKeys = true}
                //val events : Array<GitHubEvent> = json.decodeFromString(response.toString())
                //val res = Json.decodeFromString<List<SwitchAdapter.SwitchObj>>(response)
                val array: Array<String> = resources.getStringArray(R.array.coordinates)

                val res = Json.decodeFromString<LightObj>(response)
                for(r in res.rooms)
                {
                    rooms.add(RoomObj(r.id, r.name))

                    val n = r.id.replace("r", "").toInt()

                    val x1 : Int = shared.getInt(r.id+"/x1", array[n*4 + 0].toInt())
                    val y1 : Int = shared.getInt(r.id+"/y1", array[n*4 + 1].toInt())
                    val x2 : Int = shared.getInt(r.id+"/x2", array[n*4 + 2].toInt())
                    val y2 : Int = shared.getInt(r.id+"/y2", array[n*4 + 3].toInt())
                    val coord = SettingAdapter.CoordinateHolder(x1, y1, x2, y2)
                    coordinates.add(coord)
                }

                for(i in 0..<rooms.size)
                {
                    switches.add(0)
                    for(j in res.switches)
                    {
                        if(j.room == rooms[i].id)
                        {
                            switches[i]++
                        }
                    }
                }

                recyclerView.adapter = SettingAdapter(rooms, switches, coordinates)
            },
            { e ->
                e.printStackTrace()
            })

        queue.add(stringRequest)
    }
}
