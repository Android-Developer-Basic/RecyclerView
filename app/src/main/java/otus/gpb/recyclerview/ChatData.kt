package otus.gpb.recyclerview
import android.annotation.SuppressLint



enum class ChatFlags{
    VERIFY_ACCOUNT,
    MUTE,
    DELIVERED,
    VIEWED,
    NOT_OPENED,
    SCAM
}

class ChatData(private val names:Array<String>, private val messages:Array<String>, private val num:Int=0){

    companion object{

        @SuppressLint("StaticFieldLeak")

        private val images: Array<Int> = arrayOf(
            R.drawable.baraka,
            R.drawable.goro,
            R.drawable.jax,
            R.drawable.johnny,
            R.drawable.kabal,
            R.drawable.kitana,
            R.drawable.milena,
            R.drawable.noob,
            R.drawable.raiden,
            R.drawable.rax,
            R.drawable.scorpion,
            R.drawable.sektor,
            R.drawable.shang,
            R.drawable.shao,
            R.drawable.sindel,
            R.drawable.sonya,
            R.drawable.sub
        )
    }
    @SuppressLint("DiscouragedApi")
    fun createChat():Chat{
        val randomNum = (Math.random() * messages.size).toInt()
        val name = names[num]
        val flags:MutableSet<ChatFlags> = mutableSetOf()
        if(num == 12 || num == 13) flags.add(ChatFlags.VERIFY_ACCOUNT)
        if(randomNum > 8) flags.add(ChatFlags.MUTE)
        val isDelivered = randomNum == 5 || randomNum == 7
        if(isDelivered) flags.add(ChatFlags.DELIVERED)
        val isViewed = randomNum <= 4
        if(isViewed) flags.add(ChatFlags.VIEWED)
        if(num==1) flags.add(ChatFlags.SCAM)
        val isNotOpened = !isDelivered && !isViewed && randomNum %2 == 0
        if(isNotOpened) flags.add(ChatFlags.NOT_OPENED)

        val hour = (Math.random()*24).toInt()
        val minutes = (Math.random()*60).toInt()
        val time = String.format("%02d:%02d", hour, minutes)
        val numberOfMessages = if(isNotOpened) ((Math.random()*5)+1).toInt() else null

        return Chat(
            images[num],
            name,
            messages[randomNum],
            time,
            flags,
            numberOfMessages
        )
    }

}