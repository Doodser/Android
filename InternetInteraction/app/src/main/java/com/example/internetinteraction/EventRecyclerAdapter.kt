package com.example.internetinteraction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject

class EventRecyclerAdapter(private val events: List<Event>):
    RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder>() {

    @Serializable
    data class Event(val id: Int,
                     val type: String,
                     val actor: JsonObject,
                     val repo: JsonObject,
                     val payload: JsonObject,
                     val public: Boolean,
                     val createdAt: String)
    class ViewHolder(eventView: View): RecyclerView.ViewHolder(eventView) {
            val event: Event? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_event_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}