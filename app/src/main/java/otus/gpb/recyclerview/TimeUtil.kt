package otus.gpb.recyclerview

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.util.Calendar
import java.util.Date


class TimeUtil {
    companion object {
        fun getTime(date: Date): String {


            if (DateUtils.isToday(date.time)) {
                return SimpleDateFormat("HH:mm a").format(date)
            } else if (isDateInCurrentWeek(date)) {
                val outFormat = SimpleDateFormat("EEE")
                return outFormat.format(date)
            } else {
                return SimpleDateFormat("MMM dd").format(date)
            }
        }

        fun isDateInCurrentWeek(date: Date?): Boolean {
            val currentCalendar: Calendar = Calendar.getInstance()
            val week: Int = currentCalendar.get(Calendar.WEEK_OF_YEAR)
            val year: Int = currentCalendar.get(Calendar.YEAR)
            val targetCalendar: Calendar = Calendar.getInstance()
            targetCalendar.setTime(date)
            val targetWeek: Int = targetCalendar.get(Calendar.WEEK_OF_YEAR)
            val targetYear: Int = targetCalendar.get(Calendar.YEAR)
            return week == targetWeek && year == targetYear
        }
    }

}