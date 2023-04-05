package otus.gpb.recyclerview
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity



class ChatData(context: Context, private val num:Int): AppCompatActivity( ) {
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
        val markers = mutableListOf(isVerify,isMute,isDelivered,isViewed,!isDelivered && !isViewed && randomNum %2 == 0,isScam)

        return Chat(
            images[num],
            name,
            messages[randomNum],
            "${!isDelivered && !isViewed && randomNum %2 == 0}",
            markers
        )
    }



}