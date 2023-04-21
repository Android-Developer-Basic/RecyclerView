package otus.gpb.recyclerview

import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomDivider(): RecyclerView.ItemDecoration(){

    private val bounds = Rect()
    private val paint = Paint().apply {
        color = Color.LTGRAY
        strokeWidth = 2f
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val left = 73f
        val right = parent.width

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            c.drawLine(left.toFloat(), bounds.top.toFloat(), right.toFloat(), bounds.top.toFloat(), paint)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.apply {
            left = 0
            right = 0
            top = 0
            bottom = 2
        }
    }
}

