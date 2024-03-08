package com.example.lightcontrolproject

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.serialization.json.Json

class MainActivity_Menu : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        getInfoAboutRooms()
    }

    fun ButtonLightSwitchActivity(v: View?)
    {
        val activity = Intent(this, LightSwitchActivity::class.java)
        startActivity(activity)
    }

    fun ButtonRoomsActivity(v: View?)
    {
        val activity = Intent(this, RoomsActivity::class.java)
        startActivity(activity)
    }

    fun getInfoAboutRooms()
    {
        val shared: SharedPreferences = this.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)

        val queue = Volley.newRequestQueue(this)
        val url = "http://84.237.51.142:5000/light_control"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val res = Json.decodeFromString<LightObj>(response)

                shared.edit().putInt("countRooms", res.rooms.size).apply()

                for(r in res.rooms)
                {
                    //RoomsActivity.rooms.add(RoomObj(r.id, r.name))
                    shared.edit().putString("room/" + r.id.replace("r", ""), r.id).apply()
                    shared.edit().putString("room/" + r.id.replace("r", "") + "/name", r.name).apply()
                }
            },
            { e ->
                e.printStackTrace()
            })

        queue.add(stringRequest)

    }
}