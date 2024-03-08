package com.example.lightcontrolproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout.LayoutParams
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class RoomsActivity : AppCompatActivity()
{
    @Serializable
    val rooms = mutableListOf<RoomObj>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        getInfoAboutRooms()
        setInfoAndRooms()
    }

    override fun onResume()
    {
        super.onResume()

        setInfoAndRooms()
    }

    fun OpenSwitchActivity(v: View?)//короче, надо что бы назавния комнат и их id брались с сервера, пока только так
    {
        val ve : Button = v as Button

        val shared: SharedPreferences = this.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)
        shared.edit().putString("room", ve.text as String?).apply()

        val activity = Intent(this, RoomsSwitchActivity::class.java)
        startActivity(activity)

    }

    fun SwitchesFromRoom(v: View?)
    {
        val ve : TextView = v as TextView

        val shared: SharedPreferences = this.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)

        val n  = rooms.find { ve.text == it.name }?.id.toString()

        shared.edit().putString("room", n).apply()

        val activity = Intent(this, RoomsSwitchActivity::class.java)
        startActivity(activity)
    }

    fun SettingButton(v: View?)
    {
        val activity = Intent(this, SettingActivity::class.java)
        startActivity(activity)
    }

    fun ExitButton(v: View?)
    {
        finish()
    }

    fun setInfoAndRooms()
    {

        val layout = findViewById<RelativeLayout>(R.id.relativeLayout)
        val shared: SharedPreferences = this.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)

        layout.removeAllViews()

        val array: Array<String> = resources.getStringArray(R.array.coordinates)

        for(room in rooms)
        {
            val newTextView = TextView(this)

            val n = room.id.replace("r", "").toInt()

            val x1 : Int = shared.getInt(room.id+"/x1", array[n*4 + 0].toInt())
            val y1 : Int = shared.getInt(room.id+"/y1", array[n*4 + 1].toInt())
            val x2 : Int = shared.getInt(room.id+"/x2", array[n*4 + 2].toInt())
            val y2 : Int = shared.getInt(room.id+"/y2", array[n*4 + 3].toInt())

            val params = LayoutParams(x2-x1, y2-y1)

            params.setMargins(x1, y1, layout.right - x2, layout.bottom - y2)

            newTextView.layoutParams = params
            newTextView.setBackgroundColor(resources.getColor(R.color.tvBackground))

            newTextView.text = room.name
            newTextView.isClickable = true
            newTextView.setOnClickListener {
                SwitchesFromRoom(it)
            }

            layout.addView(newTextView)
        }
    }

    fun getInfoAboutRooms()
    {

        /*val queue = Volley.newRequestQueue(this)
        val url = "http://84.237.51.142:5000/light_control"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val res = Json.decodeFromString<LightObj>(response)
                for(r in res.rooms)
                {
                    rooms.add(RoomObj(r.id, r.name))
                }
            },
            { e ->
                e.printStackTrace()
            })

        queue.add(stringRequest)*/

        val shared: SharedPreferences = this.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)

        val n = shared.getInt("countRooms", -1)

        for(i in 0..<n)
        {
            val id = shared.getString("room/$i", "")
            val name = shared.getString("room/$i/name", "")

            rooms.add(RoomObj(id.toString(), name.toString()))
        }

        setInfoAndRooms()
    }
}