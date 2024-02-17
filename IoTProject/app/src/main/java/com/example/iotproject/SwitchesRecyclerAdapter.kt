package com.example.iotproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.util.Timer
import java.util.TimerTask


class SwitchesRecyclerAdapter(private val switches: List<SwitchCompat>,
                              private val switchesNames: List<String>):
    RecyclerView.Adapter<SwitchesRecyclerAdapter.ViewHolder>(){

    private val timer = Timer()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val switchName: TextView = itemView.findViewById(R.id.switchName)
        val switch: SwitchCompat = itemView.findViewById(R.id.settingSwitch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_switches_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.switchName.text = switchesNames[position]
        holder.switch.text = switches[position].text
        holder.switch.isChecked = switches[position].isChecked

        holder.switch.setOnCheckedChangeListener {compoundButton, isChecked ->
            holder.switch.isChecked = isChecked

            val queue = Volley.newRequestQueue(holder.switch.context)
            val url = "http://84.237.51.142:5000/light_control/switches/states"
            val jsonRequest = JsonObjectRequest(
                Request.Method.POST,
                url,
                JSONObject("{${holder.switch.text}:$isChecked}"),
                {response ->
                    val responseMap = Json.decodeFromString<Map<String, Boolean>>(response.toString())
                    if (responseMap[holder.switch.text] != isChecked) {
                        holder.switch.isChecked = !holder.switch.isChecked
                    }
                },
                {e ->
                    holder.switch.isChecked = !holder.switch.isChecked
                }
            )

            queue.add(jsonRequest)
        }

        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    val queue = Volley.newRequestQueue(holder.switch.context)
                    val url = "http://84.237.51.142:5000/light_control/switches/states"
                    val jsonRequest = JsonObjectRequest(
                        url,
                        {response ->
                            val responseMap = Json.decodeFromString<Map<String, Boolean>>(response.toString())
                            if (responseMap[holder.switch.text] != holder.switch.isChecked) {
                                holder.switch.isChecked = (responseMap[holder.switch.text] == true)
                            }
                        },
                        {e ->
                            e.printStackTrace()

                        }
                    )

                    queue.add(jsonRequest)
                }
            },
            100,
            2000
        )
    }

    override fun getItemCount() = switches.size
}