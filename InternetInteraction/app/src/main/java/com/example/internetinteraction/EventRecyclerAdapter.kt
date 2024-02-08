package com.example.internetinteraction

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class EventRecyclerAdapter(private val events: List<JSONObject>):
    RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder>() {

    class ViewHolder(eventView: View): RecyclerView.ViewHolder(eventView) {
            val event: JSONObject = JSONObject()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}