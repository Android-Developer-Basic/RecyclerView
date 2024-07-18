package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration: RecyclerView.ItemDecoration() {

    private val bounds = Rect()
    private val paint = Paint().apply {
        color = Color.parseColor("#8D8E90")
        strokeWidth = 2f
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            canvas.drawLine(
                210f,
                bounds.top.toFloat(),
                bounds.right.toFloat(),
                bounds.top.toFloat(),
                paint
            )
        }
    }
}