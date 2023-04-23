package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ItemRecyclerViewBinding

class ChatAdapter : RecyclerView.Adapter<ItemViewHolder> () {
    private val list = mutableListOf<ItemData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitData(data: MutableList<ItemData>) {
        list.clear()
        list.addAll(data)
    }

    fun addData(data: MutableList<ItemData>) {
        list.addAll(data)
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
    }

}