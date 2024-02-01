package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.RecyclerView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class ChatItemTouchCallback : ItemTouchHelper.Callback() {
  override fun getMovementFlags(
    recyclerView: RecyclerView,
    viewHolder: RecyclerView.ViewHolder
  ): Int = makeFlag(ACTION_STATE_SWIPE, LEFT)

  override fun onMove(
    recyclerView: RecyclerView,
    viewHolder: RecyclerView.ViewHolder,
    target: RecyclerView.ViewHolder
  ): Boolean = false

  override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    when (direction) {
      LEFT -> viewHolder.chatItemsAdapter.removeItem(viewHolder.absoluteAdapterPosition)
      else -> Unit
    }
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
    RecyclerViewSwipeDecorator.Builder(
      c,
      recyclerView,
      viewHolder,
      dX,
      dY,
      actionState,
      isCurrentlyActive
    )
      .addBackgroundColor(Color.parseColor("#66A9E0"))
      .addActionIcon(R.drawable.ic_arcive)
      .create()
      .decorate()

    super.onChildDraw(
      c,
      recyclerView,
      viewHolder,
      dX,
      dY,
      actionState,
      isCurrentlyActive
    )
  }

  private val RecyclerView.ViewHolder.chatItemsAdapter: ChatItemsAdapter
    get() = bindingAdapter as? ChatItemsAdapter ?: error("Non ChatItemsAdapter")
}