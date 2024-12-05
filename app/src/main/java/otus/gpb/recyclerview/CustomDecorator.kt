package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class CustomDecorator:ItemDecoration() {

    private val bounds = Rect()
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = 220
        val right = parent.right
        val childCount = parent.childCount
        for (index in 0 until childCount) {
            val child = parent.getChildAt(index)
            parent.getDecoratedBoundsWithMargins(child,bounds)

            val positionCurrent = parent.getChildAdapterPosition(child)
            if (positionCurrent != RecyclerView.NO_POSITION) {
                val lastElementPosition = parent.adapter?.itemCount?.minus(1)
                if(positionCurrent != lastElementPosition) {
                    c.drawLine(left.toFloat(),bounds.bottom.toFloat(),right.toFloat(),bounds.bottom.toFloat(),paint)
                }
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //super.getItemOffsets(outRect, view, parent, state)
        val padding = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            5f,
            view.context.resources.displayMetrics
        )
        outRect.apply {
            left = padding.toInt()
            right = padding.toInt()
            top = padding.toInt()
            bottom = padding.toInt()
        }
    }
}