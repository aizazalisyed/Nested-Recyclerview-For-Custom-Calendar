package com.java.nestedrecyclerviewforcustomcalendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.java.nestedrecyclerviewforcustomcalendar.databinding.CalendarCellBinding

class CalendarAdapter(private val daysOfMonth: ArrayList<String>) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CalendarCellBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(day: String) {
            binding.cellDayText.text = day
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CalendarCellBinding.inflate(inflater, parent, false)
        val layoutParams = binding.root.layoutParams
        layoutParams.height = (parent.height * 0.166666666).toInt()
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = daysOfMonth[position]
        holder.bind(day)
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }
}