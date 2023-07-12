package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private val adapter by lazy { Adapter() }         //создание адаптера
    private val presenter by lazy { Presenter() }     //создание данных
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this.applicationContext   //получаем контекст активити????

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)  //подгружаем разметку ресайклера
        recyclerView.adapter = adapter                                    //добавляет адаптер???
        adapter.setItems(presenter.getItems())

        val manager = LinearLayoutManager(this)
        //LinearLayoutManager - упорядочивает элементы в виде обычного вертикального или горизонтального списка указывать обязательно или тут или в хмл
        recyclerView.layoutManager = manager


        val dividerDrawable = AppCompatResources.getDrawable(this, R.drawable.divider)                    //получили изображение?
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {//запись в ресайклер
                dividerDrawable?.let { setDrawable(it) }
            })



        val paging = ScrollListener(manager).apply {
            onLoadMore = {
                Toast.makeText(this@MainActivity, "Еще 10", Toast.LENGTH_SHORT).show()
                adapter.addItems(presenter.getItems(adapter.itemCount))
                isLoading = false
            }
        }
        recyclerView.addOnScrollListener(paging)





        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){          //скип влево удалить элемент

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
            ): Int {
                return makeMovementFlags(ItemTouchHelper.DOWN, ItemTouchHelper.LEFT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                adapter.removeItem(position)
            }

            override fun onChildDraw(                           //  картинка присвайпе, наверно всегда одинаковая схема
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top

                val icon = ContextCompat.getDrawable(context, R.drawable.dond_lod)
                val background = ColorDrawable()
                background.color = ContextCompat.getColor(context, R.color.main)

                val iconMargin = (itemHeight - icon!!.intrinsicHeight) / 2    //прогресс свайпа???
                val iconTop = itemView.top + iconMargin
                val iconBottom = iconTop + icon.intrinsicHeight
                val iconRight = itemView.right - iconMargin
                val iconLeft = iconRight - icon.intrinsicWidth

                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)

                background.draw(c)    //рисуем иконку
                icon.draw(c)

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)  //рисуем по дефолту?
            }


        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}