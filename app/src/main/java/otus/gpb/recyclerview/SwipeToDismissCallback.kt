package otus.gpb.recyclerview

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDismissCallback(
    private val adapter: ChatAdapter, private val resources: Resources
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            0, ItemTouchHelper.LEFT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val background = ColorDrawable(Color.parseColor("#66A9E0"))

        background.setBounds(
            itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom
        )
        background.draw(canvas)
        fun dpToPx(dp: Int): Int = (resources.displayMetrics.density * dp + 0.5f).toInt()

        val archiveIcon = ContextCompat.getDrawable(itemView.context, R.drawable.archive)
        val iconHeight = archiveIcon?.intrinsicHeight!!

        val iconTop = itemView.top + dpToPx(16)
        val iconBottom = iconTop + iconHeight
        val iconRight = itemView.right - dpToPx(18)
        val iconLeft = iconRight - archiveIcon.intrinsicWidth

        archiveIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        archiveIcon.draw(canvas)

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.25f
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.removeItem(viewHolder.adapterPosition)
    }
}