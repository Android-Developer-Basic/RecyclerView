package otus.gpb.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ChatTlgAdapter(  ):RecyclerView.Adapter<ChatTlgViewHolder>(){

    private var ChatItemList = mutableListOf<ChatTlgItem>()
    fun setList(list: List<ChatTlgItem>) {
        ChatItemList = list.toList() as MutableList<ChatTlgItem>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatTlgViewHolder {

        val view = LayoutInflater.from(parent.context)
        val binding = view.inflate( R.layout.item_tlg_chat, parent, false )
        return ChatTlgViewHolder( binding  )
    }

    override fun getItemCount(): Int {
        return ChatItemList.size
    }

    override fun onBindViewHolder( chatViewHolder: ChatTlgViewHolder, position: Int) {
        chatViewHolder.bind( ChatItemList[position] )
    }
    fun onItemSwipe( position:Int, direction:Int ){
        ChatItemList.removeAt(position)
        notifyItemRemoved(position)
    }
}
