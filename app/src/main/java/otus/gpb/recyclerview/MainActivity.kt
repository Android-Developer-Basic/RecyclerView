package otus.gpb.recyclerview

import android.graphics.Canvas
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlin.random.Random
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val chatList = mutableListOf<ChatItem>()
    private val adapter = ChatAdapter()

    private var lastId = 0
    private var previousItemsCount = 0
    private var pageLoad = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        setupSwipe()
        setScrollListener()

        createItems(20)
    }

    private fun setupSwipe() {

        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                removeItem(viewHolder.adapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive
                ).create().decorate()

                super.onChildDraw(
                    c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive
                )
            }
        })

        helper.attachToRecyclerView(binding.recyclerView)
    }

    private fun setScrollListener() {

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val itemsCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (pageLoad && itemsCount > previousItemsCount) {
                    pageLoad = false
                    previousItemsCount = itemsCount
                }

                if (!pageLoad && lastVisibleItem >= itemsCount - 5) {
                    getNextPage()
                }
            }
        })
    }

    private fun getNextPage() {
        pageLoad = true
        createItems(20)
    }

    private fun removeItem(position: Int) {
        chatList.removeAt(position)
        adapter.submitList(chatList.toList())
    }

    private fun createItems(size: Int) {

        repeat(size) {

            val hour = Random.nextInt(0, 24)
            val minute = Random.nextInt(0, 60)
            val time = "%02d:%02d".format(hour, minute)

            chatList.add(
                ChatItem(
                    id = lastId++,
                    name = "Mr. Meeseeks $lastId",
                    status = "status $lastId",
                    message = "Message $lastId",
                    counter = Random.nextInt(10),
                    verified = Random.nextBoolean(),
                    scam = Random.nextBoolean(),
                    mute = Random.nextBoolean(),
                    delivered = Random.nextBoolean(),
                    read = Random.nextBoolean(),
                    time = time
                )
            )
        }

        adapter.submitList(chatList.toList())
    }
}