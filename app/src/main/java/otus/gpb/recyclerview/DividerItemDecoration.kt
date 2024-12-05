import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.R

class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val dividerPaint: Paint = Paint()

    init {
        dividerPaint.color = ContextCompat.getColor(context, R.color.divider_color) // Цвет полосы
        dividerPaint.strokeWidth = 1F // Ширина полосы
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + dividerPaint.strokeWidth
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom, dividerPaint)
        }
    }
}
