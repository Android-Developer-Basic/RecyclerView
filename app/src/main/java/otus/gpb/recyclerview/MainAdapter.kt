package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.model.MainModel

class MainAdapter : RecyclerView.Adapter<MainViewHolder>()  {

    private var items = mutableListOf<MainModel>()

    fun setItems(newItems: List<MainModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(items: List<MainModel>) {
        val size = this.items.size
        this.items.addAll(items)
        notifyItemInserted(size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutRes = R.layout.item
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun removeItem(position: Int) {
        val newItems = items.toMutableList()
        newItems.removeAt(position)
        items = newItems
        notifyItemRemoved(position)
    }

}