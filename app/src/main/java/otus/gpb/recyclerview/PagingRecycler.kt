package otus.gpb.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingRecycler(
    private val layoutManager: LinearLayoutManager
): RecyclerView.OnScrollListener() {
    var isLoading = false
    private var onLoadMore: (() -> Unit)? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = layoutManager.itemCount
        val visibleItemCount = layoutManager.childCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading) {
            if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                isLoading = true
                onLoadMore?.invoke()
            }
        }
    }

    fun setOnLoadMoreListener(callback: () -> Unit) {
        onLoadMore = callback
    }
}