package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val listView: RecyclerView by lazy { findViewById(R.id.recyclerView) }

    private val adapter: ChatAdapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val defDivider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            AppCompatResources.getDrawable(this@MainActivity, R.drawable.divider)
                ?.let { setDrawable(it) }
        }
//        listView.addItemDecoration(defDivider)
        listView.addItemDecoration(ChatItemDecoration(applicationContext))
        ItemTouchHelper(ChatItemTouchCallback()).attachToRecyclerView(listView)
        listView.adapter = adapter
        adapter.submitList(fillChat())
    }

    private fun fillChat(): List<Chat> = listOf(
        Chat(
            title = "Pizza",
            subtitle = "jija",
            text = "Yes, they are necessary",
            dateTimeText = "11:38 AM"
        ),
        Chat(
            title = "Elon",
            text = "I love /r/Reddit!",
            dateTimeText = "12:44 AM"
        ),
    )
}