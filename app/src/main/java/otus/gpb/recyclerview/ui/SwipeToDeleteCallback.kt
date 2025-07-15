package otus.gpb.recyclerview.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.R

class SwipeToDeleteCallback(
    private val onItemRemoved: (Int) -> Unit,
    context: Context
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete)!!
    private val backgroundPaint = Paint().apply {
        color = 0xFFE64646.toInt()
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.85f
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onItemRemoved(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top

        if (dX != 0f) {
            val backgroundRect = if (dX > 0) {
                RectF(itemView.left.toFloat(), itemView.top.toFloat(), itemView.left + dX, itemView.bottom.toFloat())
            } else {
                RectF(itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            }
            c.drawRect(backgroundRect, backgroundPaint)
        }

        val icon = deleteIcon
        val iconTop = itemView.top + (itemHeight - icon.intrinsicHeight) / 2

        if (dX > 0) {
            val iconLeft = itemView.left + dX.toInt() - icon.intrinsicWidth - 32
            val iconRight = iconLeft + icon.intrinsicWidth
            icon.setBounds(iconLeft, iconTop, iconRight, iconTop + icon.intrinsicHeight)
            icon.draw(c)
        } else if (dX < 0) {
            val iconRight = itemView.right + dX.toInt() + icon.intrinsicWidth + 32
            val iconLeft = iconRight - icon.intrinsicWidth
            icon.setBounds(iconLeft, iconTop, iconRight, iconTop + icon.intrinsicHeight)
            icon.draw(c)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}

