package com.example.httptest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import java.net.URL


class EventRecyclerAdapter(private val events: Array<Event>):
    RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder>() {

    @Serializable
    data class Event(val id: Long,
                     val type: String,
                     val actor: JsonObject,
                     val repo: JsonObject)

    class ViewHolder(eventView: View): RecyclerView.ViewHolder(eventView) {
        val avatar: ImageView = eventView.findViewById(R.id.avatarView)
        val author: TextView = eventView.findViewById(R.id.authorTextView)
        val repo: TextView = eventView.findViewById(R.id.repoTextView)
        val type: TextView = eventView.findViewById(R.id.typeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_event_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlobalScope.async {
            try {
                val input = URL(events[position].actor["avatar_url"].toString().replace("\"", "")).openStream()
                holder.avatar.setImageBitmap(BitmapFactory.decodeStream(input))
            } catch (e: Exception) {
                Log.e("Error", e.message!!)
                e.printStackTrace()
            }
        }

        holder.author.text = events[position].actor["display_login"].toString().replace("\"", "")
        holder.repo.text = events[position].repo["name"].toString().replace("\"", "")
        holder.type.text = events[position].type
    }
}