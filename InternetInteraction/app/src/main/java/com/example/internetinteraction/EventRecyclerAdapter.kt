package com.example.internetinteraction

import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.Serializable
import org.json.JSONObject

class EventRecyclerAdapter(private val events: List<Event>):
    RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder>() {

    @Serializable
    data class Event(val author: String, val repo: String, val type: String)
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