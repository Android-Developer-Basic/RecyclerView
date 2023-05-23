package otus.gpb.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyPageScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var onLoadMore: (() -> Unit)? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = layoutManager.itemCount
        val visibleItemCount = layoutManager.childCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if ((visibleItemCount  + firstVisibleItemPosition) >= (totalItemCount - ITEM_COUNT_OFFSET)) {
            onLoadMore?.invoke()
        }
    }

    fun setOnLoadMoreListener(callback: () ->Unit) {
        onLoadMore = callback
    }

    companion object{
        private const val ITEM_COUNT_OFFSET = 10
    }

}