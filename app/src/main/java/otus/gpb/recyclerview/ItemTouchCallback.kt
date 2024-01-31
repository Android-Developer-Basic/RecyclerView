package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class TouchCallback(private val adapter: ChatAdapter): ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.removeChat(viewHolder.adapterPosition)
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
        val backGround = ColorDrawable()
        val archiveIcon = ContextCompat.getDrawable(recyclerView.context, R.drawable.baseline_archive_24)

        backGround.setBounds(
            itemView.right + dX.toInt(),
            itemView.top,
            itemView.right,
            itemView.bottom
        )
        backGround.color = ContextCompat.getColor(recyclerView.context, R.color.blue)
        backGround.draw(c)

        val iconMargin = (itemHeight - archiveIcon!!.minimumHeight) / 2
        val iconTop = itemView.top + (itemHeight - archiveIcon.minimumHeight) / 2
        val iconLeft = itemView.right - iconMargin - archiveIcon.minimumWidth
        val iconRight = itemView.right - iconMargin
        val iconBottom = iconTop + archiveIcon.minimumHeight

        archiveIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        archiveIcon.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}