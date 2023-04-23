package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class DividerDecoration : ItemDecoration() {
    private val bounds = Rect()
    private val paint = Paint().apply {
        color = Color.GRAY
        strokeWidth = 1f
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = 200
        val right = parent.width
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)

            val positionCurrent = parent.getChildAdapterPosition(child)
            val lastElementPosition = parent.adapter?.itemCount

            if (positionCurrent != lastElementPosition) {
                c.drawLine(
                    left.toFloat(),
                    bounds.top.toFloat(),
                    right.toFloat(),
                    bounds.top.toFloat(),
                    paint
                )
            }
        }
    }

}