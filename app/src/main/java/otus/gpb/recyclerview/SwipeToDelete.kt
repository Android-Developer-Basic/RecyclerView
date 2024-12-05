package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDelete(private val adapter: ChatAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        adapter.removeItem(position)
    }

    override fun onChildDraw(c: Canvas,
                             recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder,
                             dX: Float,
                             dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {
        val itemHeight = viewHolder.itemView.height
        val background = ColorDrawable()
        val icon = ContextCompat.getDrawable(recyclerView.context, R.drawable.baseline_archive_24)

        val iconMargin = (itemHeight - icon!!.intrinsicHeight) / 2
        val iconTop = viewHolder.itemView.top + iconMargin
        val iconBottom = iconTop + icon.intrinsicHeight
        val iconRight = viewHolder.itemView.right - iconMargin
        val iconLeft = iconRight - icon.intrinsicWidth

        background.color = ContextCompat.getColor(recyclerView.context, R.color.blue)
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        background.setBounds(viewHolder.itemView.right + dX.toInt(),
                             viewHolder.itemView.top,
                             viewHolder.itemView.right,
                             viewHolder.itemView.bottom)

        background.draw(c)
        icon.draw(c)


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}