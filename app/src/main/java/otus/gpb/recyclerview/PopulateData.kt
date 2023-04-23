package otus.gpb.recyclerview

import java.text.SimpleDateFormat
import java.util.Date

import kotlin.random.Random

class PopulateData {
    companion object {
        private val names = listOf(
            "Иван",
            "Петя",
            "Вася",
            "Оля",
            "Саша",
            "Даня",
            "Наташа",
            "Катя",
            "Алексей",
            "Алексия",
            "Ален",
            "Алеся",
            "Алика",
            "Алина",
            "Наум",
            "Неонила",
            "Нестор",
            "Ника",
            "Никандр"
        )
        private val messages = listOf(
            "Жили-были старик со старухой.",
            "Вот и просит старик:",
            "Испеки мне, старая, колобок.",
            "Да из чего испечь-то? Муки нет.",
            "Эх, старуха. По амбару помети, ",
            "по сусечкам поскреби — вот и наберётся.",
            "Старушка так и сделала: ",
            "намела, наскребла горсти две муки, ",
            "замесила тесто на сметане, ",
            "скатала колобок, изжарила его в масле и ",
            "положила на окно простынуть.",
            "Надоело колобку лежать — он и покатился с окна на лавку, ",
            "с лавки на пол — да к двери, ",
            "прыг через порог, в сени, из сеней на крыльцо, ",
            "с крыльца на двор, а там и за ворота, ",
            "дальше и дальше.",
            "Катится колобок по дороге, ",
            "а навстречу ему заяц:",
            "Колобок, колобок! Я тебя съем!",
            "Нет, не ешь меня, косой, а лучше послушай, ",
            "какую я тебе песенку спою.",
            "Заяц уши поднял, ",
            "а колобок запел:",
            "Я колобок, колобок,",
            "По амбару метён,",
            "По сусечкам скребён,",
            "На сметане мешён,",
            "В печку сажён,",
            "На окошке стужён.",
            "Я от дедушки ушёл,",
            "Я от бабушки ушёл,",
            "От тебя, зайца,",
            "Не хитро уйти."
        )
        private val caption = listOf(
        "Пора вставать!",
        "Время перемен.",
        "Всё получаешь – когда ничего не ждёшь.",
        "Делаешь – не бойся.",
        "Я просто счастлива.",
        "Женщины не мыслят – они замышляют!",
        "Я у мамы дурочка.",
        "Чай или кофе? Вот в чём вопрос.",
        "Живи достойно.",
        "Каждому своё.",
        "Меняйся сам, а не меняй статусы!",
        "Я белый и пушистый!",
        "Люблю выходные.",
        "Мало – не много.",
        "Если ты упадёшь – я буду рядом. Твой асфальт.",
        "В активном поиске денег…",
        "Ни дня без подвига.",
        "Кто ел из моей миски?")



        private val avatars = listOf(
            R.drawable.avatar,
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3,
            R.drawable.avatar4,
            R.drawable.avatar5,
            R.drawable.avatar6
        )

        private val dateTime = Date()
        private val timeFormatter = SimpleDateFormat("hh:mm")
        private val dayFormatter = SimpleDateFormat("EE")
        fun populateMore(num:Int): MutableList<ItemData> {
            val dataList = mutableListOf<ItemData>()
            for (i in 1..num) {
                val verified = Random.nextBoolean()
                dateTime.time = Random.nextLong()

                dataList.add(
                    ItemData(
                        name = names.random(),
                        avatar = avatars.random(),
                        message = messages.random(),
                        caption =if (Random.nextBoolean()) caption.random() else null,
                        time = if(Random.nextBoolean()) timeFormatter.format(dateTime) else dayFormatter.format(dateTime),
                        isMute = Random.nextBoolean(),
                        isRead = Random.nextBoolean(),
                        isVerified = verified,
                        isChecked = Random.nextBoolean(),
                        isScam = if(verified) false else Random.nextBoolean(),
                        count = if (Random.nextBoolean()) Random.nextInt(1, 9) else null
                    )
                )
            }
            return dataList
        }
    }
}