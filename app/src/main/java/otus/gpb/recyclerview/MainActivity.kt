package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val listView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val adapter: ChatAdapter by lazy { ChatAdapter() }
    private val chatList = Contacts().getList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.addItemDecoration(CustomDecorator())

        listView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            val lastVisibleItemPos =
                ((view as RecyclerView).layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (lastVisibleItemPos == adapter.itemCount - 1)
                adapter.addChat(chatList)
        }

        ItemTouchHelper(ItemTouchCallback(adapter)).attachToRecyclerView(listView)

        listView.adapter = adapter
        adapter.setChat(chatList)
    }
}
