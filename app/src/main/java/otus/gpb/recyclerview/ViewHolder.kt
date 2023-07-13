package otus.gpb.recyclerview

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.model.movieModel

class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {   // Он служит своеобразным контейнером для всех компонентов, которые входят в элемент списка

    fun bind(model: movieModel){                                          //заплоняет модельки списка

        itemView.findViewById<ImageView>(R.id.avtar).setImageResource(model.avatar)    //загружает изображение по идентификатору ресурса

        itemView.findViewById<ImageView>(R.id.imageView_preview).apply {
            if (model.preview == null)                                                 //если изображение нет в модели
                visibility = View.GONE                                                 //вью невидим и не занимает место на разметке
            else {
                visibility = View.VISIBLE                                              //иначе вью видима и
                setImageResource(model.preview)                                        //загрузка изображения
            }
        }

        itemView.findViewById<ImageView>(R.id.imageView_check).apply {
            if (model.check == null)
                visibility = View.GONE
            else {
                visibility = View.VISIBLE
                setImageResource(model.check)
            }
        }

        itemView.findViewById<TextView>(R.id.textView_nick).text = model.nick

        itemView.findViewById<TextView>(R.id.textView_subLine).text = model.status

        itemView.findViewById<TextView>(R.id.textView_lastMas).text = model.last_massage

        itemView.findViewById<TextView>(R.id.textView_time).text = model.time


        itemView.findViewById<ImageView>(R.id.imageView_circle).apply {
            visibility = if (model.count == 0)                                              //если count == 0 то
                View.GONE                                                                   //visibility = View.GONE (не видно)
            else {
                View.VISIBLE                                                                //иначе visibility = View.VISIBLE (видим на экране)
            }
        }
        itemView.findViewById<TextView>(R.id.textView_count).apply {
            if (model.count == 0)
                visibility = View.GONE
            else {
                visibility = View.VISIBLE
                text = model.count.toString()
            }
        }

        itemView.findViewById<ImageView>(R.id.imageView_scam_patch).apply {
            visibility = if (model.isSCAM)
                View.VISIBLE
            else
                View.GONE
        }

        itemView.findViewById<ImageView>(R.id.imageView_mute_icon).apply {
            visibility = if (model.isMute)
                View.VISIBLE
            else
                View.GONE
        }

        itemView.findViewById<ImageView>(R.id.imageView_verified_icon).apply {
            visibility = if (model.isVerified)
                View.VISIBLE
            else
                View.GONE
        }

//        View.VISIBLE - видим на экране
//        View.INVISIBLE - не видим, но занимает место на разметке
//        View.GONE - невидим и не занимает место на разметке (полностью скрыт)
    }

}