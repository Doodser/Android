package com.example.iotproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import org.json.JSONObject


class SwitchesRecyclerAdapter(private val switches: List<SwitchCompat>):
    RecyclerView.Adapter<SwitchesRecyclerAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val switch: SwitchCompat = itemView.findViewById(R.id.settingSwitch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_switches_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.switch.text = switches[position].text
       holder.switch.isChecked = switches[position].isChecked

       holder.switch.setOnCheckedChangeListener { compoundButton, isChecked ->
           holder.switch.isChecked = isChecked

           val queue = Volley.newRequestQueue(holder.switch.context)
           val url = "http://84.237.51.142:5000/light_control/switches/states"
           val stringRequest = JsonObjectRequest(
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

           queue.add(stringRequest)
       }

    }

    override fun getItemCount() = switches.size
}