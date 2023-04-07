package otus.gpb.recyclerview
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity



class ChatData(context: Context, private val num:Int): AppCompatActivity( ) {
    private val stateFlags = listOf(
        "VERIFY_ACCOUNT",
        "MUTE",
        "DELIVERED",
        "VIEWED",
        "NOT_OPENED",
        "SCAM"
    )
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
    private val names = context.resources.getStringArray(R.array.names)
    private val messages = context.resources.getStringArray(R.array.messages)

    fun getSize() = names.size


    @SuppressLint("DiscouragedApi")
    fun createChat():Chat{
        val randomNum = (Math.random() * messages.size).toInt()
        val name = names[num]
        val isVerify = num == 12 || num == 13
        val isMute = randomNum > 8
        val isDelivered = randomNum == 5 || randomNum == 7
        val isViewed = randomNum <= 4
        val isScam = num==1
        val isNotOpened = !isDelivered && !isViewed && randomNum %2 == 0
        val markers = mutableListOf(isVerify, isMute, isDelivered, isViewed, isNotOpened, isScam)
        val flags = mutableListOf<String>()
        markers.forEachIndexed {index, item ->
            if(item) flags.add(stateFlags[index])
        }
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