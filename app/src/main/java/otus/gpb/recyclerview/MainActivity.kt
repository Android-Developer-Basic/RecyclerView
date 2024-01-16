package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val listView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val adapter: ChatAdapter by lazy { ChatAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView.adapter = adapter

        val dividerDrawable = AppCompatResources.getDrawable(this, R.drawable.divider)
        val defDivider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            dividerDrawable?.let { setDrawable(it) }
        }
        listView.addItemDecoration(defDivider)
        ItemTouchHelper(TouchCallback(adapter)).attachToRecyclerView(listView)
        adapter.setItems(fillList())
    }

    private fun fillList(): List<ChatItem> {
        val mutableList = mutableListOf<ChatItem>(
            ChatItem(
                avatar = R.drawable.pizza_avatar,
                name = "Pizza",
                messageAuthor = "jija",
                withMessagePicture = true,
                withMuteIcon = true,
                message = "Yes, they are necessary",
                date = "11:38 AM",
                messageReadIcon = 0
            ),
            ChatItem(
                avatar = R.drawable.mask_avatar,
                name = "Elon",
                message = "I love /r/Reddit!",
                date = "12:44 AM",
                withMuteIcon = true,
                messageReadIcon = 0
            ),
            ChatItem(
                avatar = R.drawable.durov_avatar,
                name = "Pasha",
                message = "How are u?",
                date = "Fri",
                withMuteIcon = true,
                withVerifyIcon = true,
                messageReadIcon = 0
            ),
            ChatItem(
                avatar = R.drawable.telegramm_avatar,
                name = "Telegram Support",
                message = "Yes it happened.",
                messageAuthor = "Support",
                date = "Thu",
                withVerifyIcon = true,
                withMessageCountIcon = true,
                messageReadIcon = 0
            ),
            ChatItem(
                avatar = R.drawable.karina_avatar,
                name = "Karina",
                message = "Okay",
                date = "Wed",
                messageReadIcon = R.drawable.unread_icon
            ),
            ChatItem(
                avatar = R.drawable.monro_avatar,
                name = "Marilyn",
                message = "Will it ever happen",
                date = "May 02",
                messageReadIcon = R.drawable.read_icon,
                isScam = true
            )
        )
        for (i in 1..25) {
            val chat = ChatItem(
                avatar = R.drawable.baseline_account_circle_24,
                name = "Someone",
                message = "Hided message",
                date = "May $i",
                messageReadIcon = R.drawable.read_icon,
                isScam = true
            )
            mutableList.add(chat)
        }
        return mutableList
    }
}