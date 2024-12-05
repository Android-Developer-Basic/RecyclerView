package otus.gpb.recyclerview.converter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatBinding
import otus.gpb.recyclerview.model.Contact
import otus.gpb.recyclerview.model.MessageStatusEnum
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ContactConverter : RecyclerView.Adapter<ContactConverter.ChatItemViewHolder>() {
    private var items: MutableList<Contact> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatBinding.inflate(inflater, parent, false)
        return ChatItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(list: List<Contact>) {
        items.clear()
        addList(list)
    }
    fun addList(list: List<Contact>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun  remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ChatItemViewHolder(private val binding: ChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Contact) {
            binding.title.text = item.title
            binding.img.setImageResource(item.imgRes)
            binding.isMuted.visibility = if (item.isMuted) View.VISIBLE else View.GONE

            binding.status.visibility = if (item.isScum ) View.VISIBLE else View.GONE
            binding.statusValue.visibility = if (item.isScum) View.VISIBLE else View.GONE

            if (item.message != null) {
                binding.subject.visibility = View.VISIBLE
                binding.lastMessageContainer.visibility = View.VISIBLE

                if (!item.subject.isEmpty()) {
                    binding.subject.visibility = View.VISIBLE
                    binding.subject.text = item.subject
                } else {
                    binding.subject.visibility = View.GONE
                }
                binding.lastMessageText.text = item.message.title

                if (item.message.hasImg) {
                    binding.lastMessageImg.visibility = View.VISIBLE
                } else {
                    binding.lastMessageImg.visibility = View.GONE
                }
            } else {
                binding.subject.visibility = View.GONE
                binding.lastMessageContainer.visibility = View.GONE
            }

            when (item.message?.status) {

                MessageStatusEnum.SEND -> {
                    binding.messageSendAndRead.visibility = View.GONE
                    binding.messageSendAndUnread.visibility = View.VISIBLE
                }

                MessageStatusEnum.DELIVERED -> {
                    binding.messageSendAndUnread.visibility = View.GONE
                    binding.messageSendAndRead.visibility = View.GONE
                }

                MessageStatusEnum.READ -> {
                    binding.messageSendAndRead.visibility = View.VISIBLE
                    binding.messageSendAndUnread.visibility = View.GONE
                }
            }



            if (item.unReadCount > 0) {
                binding.unreadBudge.visibility = View.VISIBLE
                binding.unreadValue.text = "${item.unReadCount}"
            } else {
                binding.unreadBudge.visibility = View.GONE
            }

            binding.date.text = item.message?.date?.let { getTime(it) }
        }
    }

    fun getTime(date: Date): String {


        if (DateUtils.isToday(date.time)) {
            return SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(date)
        } else if (isDateInCurrentWeek(date)) {
            val outFormat = SimpleDateFormat("EEE",Locale.ENGLISH)
            return outFormat.format(date)
        } else {
            return SimpleDateFormat("MMM dd",Locale.ENGLISH).format(date)
        }
    }

    fun isDateInCurrentWeek(date: Date?): Boolean {
        val currentCalendar: Calendar = Calendar.getInstance()
        val week: Int = currentCalendar.get(Calendar.WEEK_OF_YEAR)
        val year: Int = currentCalendar.get(Calendar.YEAR)
        val targetCalendar: Calendar = Calendar.getInstance()
        if (date != null) {
            targetCalendar.time = date
        }
        val targetWeek: Int = targetCalendar.get(Calendar.WEEK_OF_YEAR)
        val targetYear: Int = targetCalendar.get(Calendar.YEAR)
        return week == targetWeek && year == targetYear
    }
}