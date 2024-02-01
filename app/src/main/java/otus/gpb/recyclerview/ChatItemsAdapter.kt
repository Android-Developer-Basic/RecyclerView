package otus.gpb.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatItemsAdapter : RecyclerView.Adapter<ChatItemViewHolder>() {
  private var items: List<ChatItem> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ChatItemViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.layout_chat_preview, parent, false)
    return ChatItemViewHolder(view)
  }

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
    holder.bind(items[position])
  }

  @SuppressLint("NotifyDataSetChanged")
  fun setItems(items: List<ChatItem>) {
    this.items = items
    notifyDataSetChanged()
  }

  fun removeItem(position: Int) {
    this.items = items.toMutableList().apply {
      removeAt(position)
    }
    notifyItemRemoved(position)
  }
}