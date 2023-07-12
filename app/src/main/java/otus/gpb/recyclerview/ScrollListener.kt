package otus.gpb.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

    class ScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

        var isLoading = false
        var onLoadMore: (() -> Unit)? = null  //функциональный тип

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val totalItemCount = layoutManager.itemCount //Возвращает общее количество элементов в наборе данных, удерживаемом адаптером.
            val visibleItemCount = layoutManager.childCount//Возвращает количество дочерних элементов в группе.
            val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
            //Возвращает позицию адаптера первого видимого вида.

            if (!isLoading) {
                if ((visibleItemCount + firstVisiblePosition) >= totalItemCount) {
                    isLoading = true
                    onLoadMore?.invoke()
                } //вызов лямбда выражения с помощью invoke()?
            }
        }
    }
