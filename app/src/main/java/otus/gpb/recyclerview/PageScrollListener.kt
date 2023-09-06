package otus.gpb.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    var loading = false
    var onLoadMore: (()-> Unit)? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val itemsCount = layoutManager.itemCount
        val visibleItemsCount = layoutManager.childCount
        val firstVisiblePos = layoutManager.findFirstVisibleItemPosition()

        if(!loading){
            if((firstVisiblePos + visibleItemsCount) >= itemsCount){
                loading = true
                onLoadMore?.invoke()
            }
        }
    }

}