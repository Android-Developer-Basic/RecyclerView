package otus.gpb.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageScrollListener (private val layoutManager:LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var onLoadMore: (() -> Unit)? = null

    override fun onScrolled(recycleView:RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recycleView, dx, dy)

        val totalItemCount = layoutManager.itemCount
        val visibleItemCount = layoutManager.childCount
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        if ((visibleItemCount + firstVisiblePosition) >= totalItemCount) {
            onLoadMore?.invoke()
        }
    }

    fun setOnLoadMoreListener(callback: () -> Unit) {
        onLoadMore = callback
    }
}