package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class ChatDividerDecoration(
    dividerColor: Int,
    private val startInset: Int,
    private val height: Int,
) : RecyclerView.ItemDecoration() {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = dividerColor }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val end = parent.width - parent.paddingRight
        for (index in 0 until parent.childCount) {
            val child = parent.getChildAt(index)
            val adapterPosition = parent.getChildAdapterPosition(child)
            if (adapterPosition == RecyclerView.NO_POSITION || adapterPosition >= state.itemCount - 1) continue
            val decoratedBottom = parent.layoutManager?.getDecoratedBottom(child) ?: child.bottom
            val top = decoratedBottom + child.translationY.toInt()
            canvas.drawRect(startInset.toFloat(), top.toFloat(), end.toFloat(), (top + height).toFloat(), paint)
        }
    }
}