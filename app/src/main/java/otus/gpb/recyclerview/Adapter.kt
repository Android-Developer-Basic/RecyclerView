package otus.gpb.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.model.movieModel

class Adapter: RecyclerView.Adapter<ViewHolder>() {    //создает viewHolder, переписывает его

    private var item = mutableListOf<movieModel>()     //изменяемый списко типа movieModel

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<movieModel>) {
        item = newItems.toMutableList()                //toMutableList создает изменяемый список из newItems (Результат выполнения - новая коллекция, но с элементами из исходной коллекции)???
        notifyDataSetChanged()                         //указывает адаптеру, что полученные ранее данные изменились и следует перерисовать список на экране.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {                 //просто запомнить, всегда одинаковое
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false) //создается ViewHolder(разметка)
        return ViewHolder(view)                                                                     //возвращаем вью холдер передав в него разметку?
    }

    override fun getItemCount(): Int {                                                              //возвращаем размер списка элементов вьюхолдера
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {                              //передаем данные во вьюхолдер
        holder.bind(item[position])
    }

    fun removeItem(position: Int) {          //нужен дл скипа?
        val newItems = item.toMutableList()
        newItems.removeAt(position)          //удаляем позицию по полученному номеру
        item = newItems                      //переписываем
        notifyItemRemoved(position)
    }

    fun addItems(item: List<movieModel>) {
        val size = this.item.size    //размер коллекции
        this.item.addAll(item)  //addAll() добавляет все элементы из переданного в качестве аргумента объекта в список или множество
        notifyItemInserted(size) //отслеживающие добавление, удаление или изменение позиции одного элемента???
    }
}