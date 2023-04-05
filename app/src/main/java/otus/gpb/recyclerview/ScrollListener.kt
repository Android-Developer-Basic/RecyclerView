package otus.gpb.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var load:(()->Unit)? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val firstPos = layoutManager.findFirstVisibleItemPosition()
        val totalItems = layoutManager.itemCount
        val allVisibleItems = layoutManager.childCount

        if(firstPos + allVisibleItems >= totalItems) load?.invoke()
    }

    fun loadListener(callback:()->Unit){
        load = callback
    }
}