package com.java.nestedrecyclerviewforcustomcalendar


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.java.nestedrecyclerviewforcustomcalendar.MonthAdapter
import com.java.nestedrecyclerviewforcustomcalendar.MonthData
import com.java.nestedrecyclerviewforcustomcalendar.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var selectedDate: LocalDate
    lateinit var monthDataList : ArrayList<MonthData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        monthDataList = ArrayList()
        selectedDate = LocalDate.now()

        // Add 5 previous months
        val previousMonthsList = ArrayList<MonthData>()
        for (i in 1..5) {
            selectedDate = selectedDate.minusMonths(1)
            val daysInMonth = daysInMonthArray(selectedDate)
            val monthData = MonthData(
                monthYearFromDate(selectedDate),
                daysInMonth
            )
            previousMonthsList.add(monthData)
        }
        // Reverse the previous months list and append it to the end of the main list
        previousMonthsList.reverse()
        monthDataList.addAll(previousMonthsList)


        // Add the current month
        val daysInMonth = daysInMonthArray(LocalDate.now())
        val currentMonthData = MonthData(
            monthYearFromDate(LocalDate.now()),
            daysInMonth
        )
        monthDataList.add(currentMonthData)

        // Add 5 next months
        selectedDate = LocalDate.now()
        for (i in 1..5) {
            selectedDate = selectedDate.plusMonths(1)
            val daysInMonth = daysInMonthArray(selectedDate)
            val monthData = MonthData(
                monthYearFromDate(selectedDate),
                daysInMonth
            )
            monthDataList.add(monthData)
        }

        setMonthView()
    }

    private fun setMonthView() {
        val monthAdapter = MonthAdapter(monthDataList, applicationContext)
        binding.calendarRecyclerView.adapter = monthAdapter

        // Find the position of the current month data in the list
        val currentPosition = monthDataList.indexOfFirst { it.monthYearNames == monthYearFromDate(LocalDate.now()) }

        // Scroll the RecyclerView to the position of the current month
        if (currentPosition != -1) {
            binding.calendarRecyclerView.scrollToPosition(currentPosition)
        }

    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val yearMonth = YearMonth.from(date)

        val daysInMonth = yearMonth.lengthOfMonth()

        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    private fun monthYearFromDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    fun previousMonthAction(view: View) {
        selectedDate = selectedDate.minusMonths(1)
        recreate()
    }

    fun nextMonthAction(view: View) {
        selectedDate = selectedDate.plusMonths(1)
        recreate()
    }
}
