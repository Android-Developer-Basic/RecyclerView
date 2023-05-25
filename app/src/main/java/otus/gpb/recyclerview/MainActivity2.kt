package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import otus.gpb.recyclerview.adapter.ChatAdapter
import otus.gpb.recyclerview.adapter.OnInteractionListener
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val chatAdapter: ChatAdapter by lazy {
        ChatAdapter(object : OnInteractionListener {
            override fun onBindingClick(item: ChatItem, itemPosition: Int) {
                chatAdapter.addItem(
                    item.copy(id = ++id),
                    itemPosition
                )
            }
        })
    }
    private lateinit var touchHelperCallBack: ItemTouchHelper.Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // DividerDecoration
//        val dividerDrawable = AppCompatResources.getDrawable(this,R.drawable.divider)
//        val dividerDecoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL).apply {
//            dividerDrawable?.let { setDrawable(it) }
//        }
//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = chatAdapter
//            addItemDecoration(MyDecoration())
//            addItemDecoration(dividerDecoration)
//        }
//        chatAdapter.populate(stubItems)

    }

    override fun onStart() {
        super.onStart()

        // DividerDecoration
//        val dividerDecoration =
//            MaterialDividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL).apply {
//                dividerInsetStart = 250
//                isLastItemDecorated = false
//                dividerColor = resources.getColor(R.color.grey_100, theme)
//                dividerThickness = 2
//            }

        val layoutManager = LinearLayoutManager(this@MainActivity2)
        binding.recyclerView.apply {
            this.layoutManager = layoutManager
            adapter = chatAdapter
//            addItemDecoration(dividerDecoration)
            addItemDecoration(MyDecoration(this@MainActivity2))

            addOnScrollListener(MyPageScrollListener(layoutManager).apply {
                setOnLoadMoreListener {
                    chatAdapter.addItems(stubItems, chatAdapter.itemCount)

                    Toast.makeText(
                        this@MainActivity2,
                        "Items count now is ${chatAdapter.itemCount}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            )
        }

        touchHelperCallBack = getTouchHelperCallback()
        val itemTouchHelper = ItemTouchHelper(touchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        stubItems.forEach { item ->
            item.onClickListener = {
                Toast.makeText(
                    this,
                    getString(R.string.last_message, item.userName, item.lastMessage),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        chatAdapter.populate(stubItems)
    }

    private fun getTouchHelperCallback(): ItemTouchHelper.Callback {
        val touchHelperCallBack = object : MyTouchHelperCallBack(this@MainActivity2) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                chatAdapter.swapItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = chatAdapter.list[position]

                chatAdapter.removeItem(position)

                val snackBar = Snackbar.make(
                    viewHolder.itemView,
                    "Item was removed from the list.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.setAction("RESTORE") {
                    chatAdapter.restoreItem(item, position)
                }
                snackBar.setActionTextColor(getColor(R.color.blue_400))
                snackBar.show()
            }
        }
        return touchHelperCallBack
    }

    companion object {
        private var id = 0
        private val stubItems = mutableListOf(
            ChatItem(
                id = ++id,
                userName = "Pizza",
                lastMessage = "Yes, they are necessary",
                date = "11:38 AM",
                isMuted = true
            ),
            ChatItem(
                id = ++id,
                userName = "Elon",
                lastMessage = "I love /r/ Reddit!",
                date = "12:44 AM",
                isMuted = true
            ),
            ChatItem(
                id = ++id,
                userName = "Pavel",
                lastMessage = "How are you?",
                date = "Fri",
                isMuted = true,
                isVerified = true,
                unreadMessageCount = 1111
            ),
            ChatItem(
                id = ++id,
                userName = "User X",
                lastMessage = "deelete it now",
                date = "Thu",
                isMuted = true,
                isLastMessageMine = true,
                isMessageDelivered = true,
                isMessageRead = true
            ),
            ChatItem(
                id = ++id,
                userName = "Telegram support",
                lastMessage = "Yes, it happened.",
                date = "Thu",
                isVerified = true,
                unreadMessageCount = 1
            ),
            ChatItem(
                id = ++id,
                userName = "Karinaa",
                lastMessage = "okay",
                date = "Wed",
                isLastMessageMine = true,
                isMessageDelivered = true
            ),
            ChatItem(
                id = ++id,
                userName = "Marilyn",
                lastMessage = "Will it ever happen",
                date = "May 02",
                isScam = true,
                isLastMessageMine = true,
                isMessageDelivered = true,
                isMessageRead = true
            ),
            ChatItem(
                id = ++id,
                userName = "all icons check",
                lastMessage = "Checked",
                date = "Tue",
                isScam = true,
                isVerified = true,
                isMuted = true,
                unreadMessageCount = 3,
                isLastMessageMine = true,
                isMessageDelivered = true,
                isMessageRead = true
            ),
            ChatItem(
                id = ++id,
                userName = "all icons long text check",
                lastMessage = "Checked",
                date = "Tue",
                isScam = true,
                isVerified = true,
                isMuted = true,
                unreadMessageCount = 3,
                isLastMessageMine = true,
                isMessageDelivered = true,
                isMessageRead = true
            )
        )
    }
}