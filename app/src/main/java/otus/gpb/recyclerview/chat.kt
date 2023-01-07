package otus.gpb.recyclerview

import android.graphics.drawable.Drawable
import java.util.Date

data class chat(
    val fio: String,
    val message: String,
    val status: String,
    val dateTime: String,
    val draw: Int
    )