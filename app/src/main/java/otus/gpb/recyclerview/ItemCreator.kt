package otus.gpb.recyclerview

import androidx.core.content.ContextCompat
import java.time.LocalDateTime
import java.util.*

object ItemCreator {
    fun create() = listOf(
        chat(
            fio = "Антон",
            message = "Пошли на каток",
            status = "В сети",
            dateTime = "11:38 PM",
            draw = R.drawable.ic_baseline_bathtub_24
        ),
        chat(
            fio = "Лена",
            message = "Продам гараж",
            status = "В сети",
            dateTime = "19:30 yesterday",
            draw = R.drawable.ic_baseline_baby_changing_station_24
        ),
        chat(
            fio = "Нина",
            message = "Нг отмечаем?",
            status = "В сети",
            dateTime = "now",
            draw = R.drawable.ic_baseline_cruelty_free_24
        ),
        chat(
            fio = "Артур",
            message = "Елку ставил?",
            status = "В сети",
            dateTime = "7:00 AM",
            draw = R.drawable.ic_baseline_psychology_alt_24
        ),
        chat(
            fio = "Фархад",
            message = "Есть сотка?",
            status = "В сети",
            dateTime = "last week",
            draw = R.drawable.ic_baseline_downhill_skiing_24
        ),
        chat(
            fio = "Алсу",
            message = "Идем на йогу",
            status = "В сети",
            dateTime = "10:12 PM",
            draw = R.drawable.ic_baseline_self_improvement_24
        ),
        chat(
            fio = "Рокки",
            message = "Почему не пришел на тренировку",
            status = "Не в сети",
            dateTime = "yesterday",
            draw = R.drawable.ic_baseline_settings_accessibility_24
        ),
        chat(
            fio = "Мама",
            message = "Одень шапку",
            status = "В сети",
            dateTime = "10:07 AM",
            draw = R.drawable.ic_baseline_settings_accessibility_24
        ),
        chat(
            fio = "Рината",
            message = "Король лир в драмме - невероятно",
            status = "была вчера в 7:00",
            dateTime = "yesterday",
            draw = R.drawable.ic_baseline_theater_comedy_24
        ),
        chat(
            fio = "Сосед",
            message = "Убери тапки",
            status = "В сети",
            dateTime = "00:00",
            draw = R.drawable.ic_baseline_skateboarding_24
        ),
        chat(
            fio = "Росбанкрот",
            message = "Банкротство физ лиц под ключ",
            status = "В сети",
            dateTime = "00:00",
            draw = R.drawable.ic_baseline_notifications_paused_24
        )
    )
}