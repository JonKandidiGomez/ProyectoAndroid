import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

class DateConverter {
    companion object {
        @SuppressLint("ConstantLocale")
        private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

        @TypeConverter
        fun fromDate(date: Date?): String? {
            return date?.let { formatter.format(it) }
        }

        @TypeConverter
        fun toDate(dateString: String?): Date? {
            return dateString?.let {
                try {
                    formatter.parse(it)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}