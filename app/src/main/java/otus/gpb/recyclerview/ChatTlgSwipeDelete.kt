package otus.gpb.recyclerview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class TlgItemTouchHelper(  val adapter: ChatTlgAdapter ) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.START
        return makeMovementFlags(0 , swipeFlags)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {
        val itemPosition:Int = viewHolder.adapterPosition
        adapter.onItemSwipe( itemPosition, direction )
    }
}
