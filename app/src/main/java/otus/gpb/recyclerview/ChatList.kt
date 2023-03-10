package otus.gpb.recyclerview

object ChatList {
    var pageIndex = 10
    var list = mutableListOf(
        Chat(
            1,
            "Franky",
            message = "Hey, want you money?",
            numberMessage =  0,
            isMute = true,
            isScam = true
        ),
        Chat(
            2,
            "Forever Friends",
            messageAuthor = "Daniel",
            isGroup = true,
            message = "can we get together at my place?",
            numberMessage =  1,
        ),
        Chat(
            3,
            "Emma",
            message = "You?",
            numberMessage =  3,
        ),
        Chat(
            4,
            "Basket & Coffee",
            message = "Our cupcakes are waiting for you!",
            numberMessage =  0,
            isMute = true
        ),
        Chat(
            5,
            "Telegram",
            message = "⭐️ Telegram Premium — отличный подарок к празднику \uD83C\uDF81. Подарить можно предоплаченную подписку на 3, 6 или 12 месяцев — с приятной скидкой.\n" +
                    "\n" +
                    "Получателю будут доступны все преимущества Telegram Premium: скачивание файлов без ограничения скорости \uD83D\uDE31, тысячи эмодзи из авторских наборов \uD83D\uDE0E и многое другое \uD83D\uDC4F.\n",
            numberMessage =  0,
            isVerified = true
        ),
        Chat(
            6,
            "Больше!",
            message = "Расписание на 10 марта \uD83C\uDFBE",
            numberMessage =  0,
        ),
        Chat(
            7,
            "Коммент.Шоу",
            message = "\uD83D\uDCB5 Краткий ликбез о доходах российских футбольных клубов. Внимательно слушаем лекцию от самой известной женщины-президента в нашем футболе.",
            numberMessage =  0,
            isVerified = true
        ),
        Chat(
            8,
            "Раб. чат",
            messageAuthor = "Валя",
            isGroup = true,
            message = "Зайди к нам!",
            numberMessage =  0,
            isMute = true,
            isVerified = false
        ),
        Chat(
            9,
            "ZenitCafe",
            message = "Тарантиновские диалоги подъехали.",
            numberMessage =  0,
            isMute = true,
            isVerified = false
        ),
        Chat(
            10,
            "Алина",
            message = "Привет!",
            numberMessage =  1,
            isMute = true,
            isScam = true
        ),
        Chat(
            11,
            "Бостон Селтикс",
            message = "Кто там у нас снайпер? Грант Уильямс? Сэм Хаузер?\n" +
                    "Эл Хорфорд с матча всех звёзд бросает под 60% с дальней!",
            numberMessage =  0,
            isMute = false,
            isVerified = false
        ),
        Chat(
            12,
            "Подъезд 1",
            messageAuthor = "Артур",
            isGroup = true,
            message = "Уберите это!",
            numberMessage =  5,
            isMute = true,
            isVerified = false
        ),
        Chat(
            13,
            "Подъезд 2",
            messageAuthor = "Вы",
            isGroup = true,
            message = "Я про это уже говорил",
            numberMessage =  0,
            isSend = true,
            isRead = true,
            isMute = true,
        ),
        Chat(
            14,
            "Подъезд 3",
            messageAuthor = "Мария",
            isGroup = true,
            message = "Маленький лифт не работает!",
            numberMessage =  0,
            isSend = true,
            isRead = true,
            isMute = true,
        ),
        Chat(
            15,
            "АнимеСтар",
            message = "Весенний сезон уже стартовал",
            numberMessage =  2
        ),
        Chat(
            16,
            "FlashScore",
            message = "Изменения в приложении",
            numberMessage =  20,
            isMute = true
        ),
        Chat(
            17,
            "Basket",
            messageAuthor = "Артем",
            isGroup = true,
            message = "Кто сегодня в зале?",
            numberMessage =  1,
        ),
        Chat(
            18,
            "Макс",
            message = "Привет, займи 1 к",
            numberMessage =  1,
            isMute = true
        ),
        Chat(
            19,
            "Родные",
            messageAuthor = "Мать",
            isGroup = true,
            message = "Надо скинуться на Др",
            numberMessage =  3
        ),
        Chat(
            20,
            "Офис",
            messageAuthor = "Артем",
            isGroup = true,
            message = "Встреть курьера пожалуйста",
            numberMessage =  0
        ),
        Chat(
            21,
            "8 Марта",
            messageAuthor = "Витя",
            isGroup = true,
            message = "Остались деньги, что будем делать?",
            numberMessage =  0
        ),
        Chat(
            22,
            "Android Academy Msk",
            "Денис Монся",
            message = "был бы не против поучаствовать",
            numberMessage =  642,
            isMute = true
        ),
        Chat(
            23,
            "Роман",
            message = "\uD83D\uDE05",
            numberMessage =  0
        ),
        Chat(
            24,
            "СБГ Аналитика ",
            message = "По итогам матча Ренан стал самым пасующим игроком.",
            numberMessage =  0
        ),
        Chat(
            25,
            "Полина",
            message = "Исходящий звонок",
            numberMessage =  0,
            isSend = true,
            isRead = true,
            isVerified = false
        ),
        Chat(
            26,
            "Sunday",
            message = "Лепестки выращенные морозом на маленьком озере в нашем парке",
            numberMessage =  0
        ),
        Chat(
            27,
            "HARD skills pumping",
            message = "STENET school открывает набор на онлайн курс: ",
            numberMessage =  0
        ),
        Chat(
            28,
            "Donate",
            message = "Срок вашей платной подписки закончился. Если вы хотите продлить подписку на этот канал, нажмите «Подписаться» ниже",
            numberMessage =  0,
            isVerified = true
        ),
        Chat(
            29,
            "Привет из Кореи",
            message = "Hey, want you money?",
            numberMessage =  0
        ),
        Chat(
            30,
            "Дмитрий",
            message = "да да",
            numberMessage =  0
        ),
        Chat(
            31,
            "Женя",
            message = "17.",
            numberMessage =  0
        ),
        Chat(
            32,
            "Anya",
            message = "ну норм вот",
            numberMessage =  0
        ),
        Chat(
            33,
            "Дима разраб",
            message = "не",
            numberMessage =  0
        ),
        Chat(
            34,
            "Шаман",
            message = "Если что ты сверху",
            numberMessage =  0
        ),
        Chat(
            35,
            "Настя",
            message = "Спасибо",
            numberMessage =  0
        ),
        Chat(
            36,
            "Эмма",
            message = "Отлично))",
            numberMessage =  0
        ),
        Chat(
            37,
            "Весь мир из Краснодара",
            message = "\uD83C\uDDEF\uD83C\uDDF4 Иордания = 32 100 ₽ (июнь и дальше)",
            numberMessage =  0,
            isMute = true
        ),
        Chat(
            38,
            "Mibow \uD83C\uDDF7\uD83C\uDDFA",
            message = "пожалуйста:)",
            numberMessage =  0,
            isSend = true,
            isRead = true,
        ),
        Chat(
            39,
            "НГ пирдушка",
            "примерно к часу",
            message = "Примерно к часу",
            numberMessage =  0,
            isSend = true,
            isRead = true,
        ),
        Chat(
            40,
            "idzayu",
            message = "Фотография",
            numberMessage =  0,
            isSend = true,
            isRead = true
        ),
        Chat(
            41,
            "Тимофей",
            message = "А, ну и второе тоже",
            numberMessage =  0
        ),
        Chat(
            42,
            "Aldar_Xan",
            message = "Спроси про прогу шоб модели делать для печати",
            numberMessage =  0
        ),
        Chat(
            43,
            "Anna",
            message = "А ну ничего, спасибо)",
            numberMessage =  0
        ),
        Chat(
            44,
            "Раньше всех. Ну почти.",
            message = "Парижский суд отклонил иск жены президента Франции Эмманюэля Макрона Бриджит Макрон к двум женщинам, которые во время стрима в Youtube заявляли, что она трансгендер, сообщает Le Parisien",
            numberMessage =  0
        ),
        Chat(
            45,
            "Взял Мяч",
            message = "Лучший момент Дубового Топа?",
            numberMessage =  0
        ),
        Chat(
            46,
            "Анжела",
            message = "Нужна работа?",
            numberMessage =  3,
            isScam = true
        ),
        Chat(
            47,
            "50GYM Краснодар",
            message = "Фотография",
            numberMessage =  0,
            isMute = true
        ),
        Chat(
            48,
            "Мит",
            message = "Да иногда тож смотрел его)",
            numberMessage =  0
        ),
        Chat(
            49,
            "Андрей",
            message = "Понял",
            numberMessage =  0,
            isSend = true,
            isRead = true,
        ),
        Chat(
            50,
            "Влад",
            message = "Это база",
            numberMessage =  0
        )
    )
}
