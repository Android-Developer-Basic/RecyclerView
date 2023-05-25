package otus.gpb.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import otus.gpb.recyclerview.adapter.ChatListAdapter
import otus.gpb.recyclerview.adapter.OnInteractionListener
import otus.gpb.recyclerview.adapter.Stubs
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val chatAdapter: ChatListAdapter by lazy {
        ChatListAdapter(object : OnInteractionListener {
            override fun onBindingClick(item: ChatItem, itemPosition: Int) {
                chatAdapter.addItem(item.copy(id = ++Stubs.id))
            }
        })
    }
    private lateinit var touchHelperCallBack: ItemTouchHelper.Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //due to onStart calls many times, and onCreate calls once,
        // all entities that required to be instantiated ONCE go to onCreate

        val listToSubmit = Stubs.stubItems.onEach { item ->
            item.onClickListener = {
                Toast.makeText(
                    this,
                    getString(R.string.last_message, item.userName, item.lastMessage),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val layoutManager = LinearLayoutManager(binding.recyclerView.context)
        binding.recyclerView.apply {
            this.layoutManager = layoutManager
            adapter = chatAdapter
            addItemDecoration(MyDecoration(this.context))

            addOnScrollListener(MyPageScrollListener(layoutManager).apply {
                setOnLoadMoreListener {
                    chatAdapter.addItems(listToSubmit)

                    Toast.makeText(
                        this@MainActivity,
                        "Items count now is ${chatAdapter.itemCount}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            )
        }
        chatAdapter.submitList(listToSubmit)

        touchHelperCallBack = getTouchHelperCallback()
        val itemTouchHelper = ItemTouchHelper(touchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun getTouchHelperCallback(): ItemTouchHelper.Callback {
        val touchHelperCallBack = object : MyTouchHelperCallBack(binding.recyclerView.context) {
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
                val item = chatAdapter.currentList[position]

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
}