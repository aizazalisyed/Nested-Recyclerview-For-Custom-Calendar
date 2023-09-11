package com.java.nestedrecyclerviewforcustomcalendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MonthAdapter(private val dataList: List<MonthData>, val context: Context) : RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val monthView = inflater.inflate(R.layout.month_item, parent, false) // Replace R.layout.item_month with your item layout
        return MonthViewHolder(monthView)
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {

        val monthData = dataList [position]
        holder.monthYearName.text = monthData.monthYearNames
        val layoutManager = GridLayoutManager(context, 7)
        holder.calendarRecyclerView.layoutManager = layoutManager
        holder.calendarRecyclerView.adapter = CalendarAdapter(monthData.days)


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val monthYearName = itemView.findViewById<TextView>(R.id.monthYearTV)
        val calendarRecyclerView = itemView.findViewById<RecyclerView>(R.id.calendarRecyclerView)

    }
}
