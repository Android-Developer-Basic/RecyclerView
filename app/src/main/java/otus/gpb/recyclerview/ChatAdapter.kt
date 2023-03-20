package layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import otus.gpb.recyclerview.ChatData
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatAdapter(private val list: MutableList<ChatData>): Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addChats(newChats: List<ChatData>) {
        list.addAll(newChats)
        // Почему-то не работает, UI дергается
//        val result = DiffUtil.calculateDiff(
//            object: DiffUtil.Callback() {
//                override fun getOldListSize(): Int {
//                    return list.size
//                }
//
//                override fun getNewListSize(): Int {
//                    return newChats.size
//                }
//
//                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//                    return list[oldItemPosition].id == newChats[newItemPosition].id
//                }
//
//                override fun areContentsTheSame(
//                    oldItemPosition: Int,
//                    newItemPosition: Int
//                ): Boolean {
//                    return list[oldItemPosition] == newChats[newItemPosition]
//                }
//
//            }, false
//        )
//        result.dispatchUpdatesTo(this)

        notifyDataSetChanged()
    }

    fun removeChat(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}