package com.example.scrolling

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView

class SettingsRecyclerAdapter(private val settings: List<SwitchCompat>):
    RecyclerView.Adapter<SettingsRecyclerAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val setting: SwitchCompat = itemView.findViewById(R.id.settingSwitch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_settings_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setting.text = settings[position].text
        holder.setting.isChecked = settings[position].isChecked

        val settings: SharedPreferences = holder.setting.context.getSharedPreferences("ScrollingAppPreferences",
            AppCompatActivity.MODE_PRIVATE
        )
        holder.setting.setOnCheckedChangeListener { buttonView, isChecked ->
            holder.setting.isChecked = isChecked
            settings.edit().putBoolean(holder.setting.text.toString(), isChecked).apply()
        }

    }

    override fun getItemCount() = settings.size
}