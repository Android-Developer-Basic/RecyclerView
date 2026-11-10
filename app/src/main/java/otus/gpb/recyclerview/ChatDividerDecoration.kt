package otus.gpb.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.core.R as CoreR

/**
 * Разделитель под каждой ячейкой, начинается после аватара
 */
class ChatDividerDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply { color = ContextCompat.getColor(context, CoreR.color.chat_divider) }
    private val dividerHeight = context.resources.getDimensionPixelSize(R.dimen.chat_divider_height)
    private val insetStart = context.resources.getDimensionPixelSize(R.dimen.chat_divider_inset_start)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // Резервируем место под линию, чтобы её не перекрыл фон следующей ячейки
        outRect.bottom = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = (parent.paddingLeft + insetStart).toFloat()
        val right = (parent.width - parent.paddingRight).toFloat()
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            // Учитываем анимации: при удалении/сдвиге линия едет и тает вместе с ячейкой
            val top = child.bottom + child.translationY
            paint.alpha = (child.alpha * 255).toInt()
            c.drawRect(left, top, right, top + dividerHeight, paint)
        }
    }
}
