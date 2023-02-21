package otus.gpb.recyclerview

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        ChatAdapter(list = ItemCreator.create().toMutableList())
    }

    private var hasItemAdd: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    private fun setupView() {
        binding.recycler.adapter = adapter

        val decorator = DividerItemDecoration(this, HORIZONTAL)

        binding.recycler.addItemDecoration(decorator)

        binding.recycler.setOnScrollChangeListener { recyclerView, p1, p2, p3, p4 ->

        val layoutManager = (recyclerView as RecyclerView).layoutManager
        val visibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        fetchNewData(visibleItem)

        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == LEFT) {
                    showRemoveItemDialog(viewHolder.adapterPosition)
                }
            }

        })

        itemTouchHelper.attachToRecyclerView(binding.recycler)
        }

    fun showRemoveItemDialog(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you really want to delete?")
        builder.setPositiveButton("Delete") {_, _ -> adapter.removeItem(position)}
        builder.setNegativeButton("Cancel") { item, _ -> item.dismiss()}
        builder.show()

    }


    private fun fetchNewData(visibleItem: Int) {

        if (visibleItem == adapter.itemCount - 2 && hasItemAdd) {
            hasItemAdd = false
            adapter.addItem(ItemCreator.createSecondList())
        }

    }

}

