package otus.gpb.recyclerview


import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hasItemToAdd: Boolean = true
    private val adapter by lazy {
        ChatAdapter(
        list = ChatCreator.create().toMutableList()
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
        binding.items.adapter = adapter

        val decorator = DividerItemDecoration(this, VERTICAL).apply {
            val drawable = ResourcesCompat.getDrawable(resources, R.drawable.decorator, theme) ?: return
            setDrawable(drawable)
        }

        binding.items.addItemDecoration(decorator)

        binding.items.setOnScrollChangeListener { recyclerView, p1, p2, p3, p4 ->
            val layoutManager = (recyclerView as RecyclerView).layoutManager
            val visibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            onItemScrolled(visibleItem)
        }

        val archiveIcon = ResourcesCompat.getDrawable(resources, R.drawable.package_down, null)!!

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

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView

                val iconMargin = (itemView.height - archiveIcon.intrinsicHeight) / 2

                if (dX > 0) {
                    c.drawColor(Color.GRAY)
                }
                else {
                    c.drawColor(ResourcesCompat.getColor(resources, R.color.background_archive, null))
                    archiveIcon.setBounds(itemView.right - iconMargin - archiveIcon.intrinsicWidth, itemView.top + iconMargin,
                        itemView.right - iconMargin, itemView.bottom - iconMargin)
                }
                archiveIcon.draw(c)
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.items)

    }

    fun showRemoveItemDialog(position: Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Вы действительно хотите удалить чат?")
        builder.setPositiveButton("Удалить") { _, _ -> adapter.removeItem(position) }
        builder.setNegativeButton("Отменить") { item, _ -> Cancel(position) }
        builder.show()
    }

    private fun Cancel(position: Int) {
        adapter.notifyItemRemoved(position +1)
        adapter.notifyItemRangeChanged(position, adapter.itemCount)
    }

    private fun onItemScrolled(visibleItem: Int) {
        if (visibleItem == adapter.itemCount -1 && hasItemToAdd) {
            hasItemToAdd = false
            adapter.addItem(ChatCreator.createNew())
        }
    }

}

