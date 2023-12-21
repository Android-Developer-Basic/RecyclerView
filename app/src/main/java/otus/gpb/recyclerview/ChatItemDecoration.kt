package otus.gpb.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class ChatItemDecoration(private val context: Context) : ItemDecoration() {

    private val padding = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        72F,
        context.resources.displayMetrics
    ).toInt()

    private val bounds = Rect()
    private val paint = Paint().apply {
        color = this@ChatItemDecoration.context.resources.getColor(R.color.decorator, null)
        strokeWidth = 1F
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        parent.children.forEach { view ->
            parent.getDecoratedBoundsWithMargins(view, bounds)
            parent.getChildAdapterPosition(view)
                .takeUnless { it == RecyclerView.NO_POSITION }
                ?.let {
                    c.drawLine(
                        bounds.left.toFloat() + padding,
                        bounds.bottom.toFloat(),
                        bounds.right.toFloat(),
                        bounds.bottom.toFloat(),
                        paint
                    )
                }
        }
    }

}