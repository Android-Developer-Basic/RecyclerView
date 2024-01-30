package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val rvAdapter: ChatAdapter by lazy { ChatAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(
                ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.thin_line,
                        null)!!
                                         )
        recyclerView.addItemDecoration(dividerItemDecoration)

        ItemTouchHelper(SwipeToDelete(rvAdapter)).attachToRecyclerView(recyclerView)

        rvAdapter.setItems(initItemsList())

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                with((recyclerView.layoutManager as LinearLayoutManager)) {
                    val visibleItemCount = childCount
                    val pastVisibleItem = findFirstCompletelyVisibleItemPosition()
                    val total = rvAdapter.itemCount

                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        rvAdapter.updateItems(loadMore())
                    }
                }
            }
        })
    }

    private fun initItemsList(): MutableList<Message> {
        return mutableListOf(
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM"
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:35 AM",
                        isVerified = true
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM",
                        isMuted = true
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM",
                        isVerified = true,
                        isMuted = true
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM",
                        isRead = true
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM",
                        isSent = true
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM",
                        unreadCounter = 2
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM",
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM",
                       ),
                Message(
                        name = "Pasha",
                        subtitle = "azaza",
                        message = "azazazaazazazaz",
                        avatar = R.drawable.ic_launcher_foreground,
                        dateTimeText = "12:34 AM",
                       )
                            )

    }

    private fun loadMore(): MutableList<Message> {
        val list = mutableListOf<Message>()
        for (i in 0..10) {
            list.add(
                    i, Message(
                    name = i.toString(),
                    subtitle = "azaza",
                    message = "azazazaazazazaz",
                    avatar = R.drawable.ic_launcher_foreground,
                    dateTimeText = "12:34 AM",
                              )
                    )
        }
        return list
    }
}