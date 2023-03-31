package otus.gpb.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MyDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val rect = Rect()
    private val paint = Paint().apply {
        color = context.getColor(R.color.grey_100)
        strokeWidth = 2F
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            top = 16
            bottom = 16
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val left = parent.paddingLeft  + 250
        val right = parent.width

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, rect)

//            val position =
//                parent.getChildAdapterPosition(child).let { if (it == RecyclerView.NO_POSITION) return else it }

            c.drawLine(left.toFloat(), rect.top.toFloat(), right.toFloat(), rect.top.toFloat(), paint)
        }

    }
}