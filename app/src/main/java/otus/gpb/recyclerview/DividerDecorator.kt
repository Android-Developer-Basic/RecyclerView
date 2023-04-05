package otus.gpb.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class DividerDecorator(context: Context): ItemDecoration() {
    private val bounds = Rect()
    private val paint = Paint().apply {

        color = ContextCompat.getColor(context,R.color.divider_color)
        strokeWidth = 2F
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val  left = 73
        val right = parent.width

        for(i in 0 until parent.childCount){
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)

            c.drawLine(left.toFloat(), bounds.bottom.toFloat(), right.toFloat(), bounds.bottom.toFloat(), paint)
        }

        super.onDraw(c, parent, state)
    }

}