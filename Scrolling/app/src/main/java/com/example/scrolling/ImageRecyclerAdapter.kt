package com.example.scrolling

import android.content.SharedPreferences
import android.provider.MediaStore.Images
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView

class ImageRecyclerAdapter(private val images: List<Int>):
    RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_image_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(images[position])

        holder.image.setOnClickListener {
            val settings: SharedPreferences = holder.image.context.getSharedPreferences("ScrollingAppPreferences",
                AppCompatActivity.MODE_PRIVATE
            )
            settings.edit().putInt("image", position).apply()
        }
    }

    override fun getItemCount() = images.size
}
