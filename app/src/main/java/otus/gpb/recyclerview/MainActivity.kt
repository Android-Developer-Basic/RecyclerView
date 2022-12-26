package otus.gpb.recyclerview

import android.app.Activity
import android.app.AlertDialog
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    private val adapter by lazy {
        Adapter(
            list = ItemCreator.create().toMutableList()
        )
    }

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
        binding.recyclerView.adapter = adapter
        val decorator = DividerItemDecoration(this, VERTICAL).apply {
            val drawable = ResourcesCompat.getDrawable(resources, R.drawable.decorator, theme) ?: return
            setDrawable(drawable)
        }
        binding.recyclerView.addItemDecoration(decorator)
        val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == LEFT)
                {
                    showRemoveItemDialog(viewHolder.adapterPosition)
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.addOnScrollListener(object : Pagination(binding.recyclerView.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                getMoreItems()
            }
        })
    }

    private fun showRemoveItemDialog(position: Int)
    {
       val builder = AlertDialog.Builder(this)
        builder.setMessage("Удалить диалог?")
        builder.setPositiveButton("Удалить?"){_, _->adapter.removeItem(position)}
        builder.setNegativeButton("Отмена") { _, _ ->NegativeButton(position) }
        builder.show()
    }

    private fun onRemoveButtonClicked(position: Int)
    {
        adapter.removeItem(position)
    }

    private fun getMoreItems() {
        isLoading = false
        adapter.addData()

    }

    private fun NegativeButton(position: Int) {
        adapter.notifyItemRemoved(position + 1)
        adapter.notifyItemRangeChanged(position, adapter.getItemCount())
    }
}