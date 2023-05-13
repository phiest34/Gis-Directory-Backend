package com.example.register

import com.example.models.WebMapInfo

class WebMapInfoStaticStorage {
    private val mutableMap: MutableMap<String, WebMapInfo> = mutableMapOf()

    operator fun get(assetPath: String): WebMapInfo? = mutableMap[assetPath]

    init {
        mutableMap.putAll(
            mapOf(
                "qgis_strogyno_bay" to WebMapInfo(
                    assetPath = "qgis_strogyno_bay",
                    imageUrl = "https://gis-directory.herokuapp.com/images/qgis_strogyno_bay.png",
                    description = """
                    Другие названия: Строгинский затон, Строгинская пойма, Троице-Лыковская пойма.
                    Правобережный расширенный залив р. Москвы, расположенный восточнее жилого района Строгино. Представляет собой песчаный карьер, вырытый заключёнными и заполненный водами Карамышевского водохранилища.

                    Самый широкий водоём в Москве; тянется с востока на запад на 1,9 км, а с севера на юг — на 1,25 км.

                    Площадь около 171 га, по измерениям Ю. Насимовича — 165 га.

                    Форма в самом первом приближении округлая. Находится в пределах природного парка "Москворецкий" и со всех сторон ограничен природными или озеленёнными территориями: с северо-востока, востока и юго-востока — Щукинским полуостровом (длиной 4,2 км), с юго-запада — Строгинским мысом (длиной 0,8 км), с северо-запада — основной частью Строгинской поймы.

                    Абсолютная высота уреза воды около 126 м.
                    На Строгинском мысу и особенно на Щукинском полуострове известны многочисленные виды животных и растений, занесённых в Красную книгу г. Москвы.

                    На юге соединён проливом (длиной 340 м, шириной 80-280 м) с р. Москвой. Образует несколько коротких заливов, в т.ч. северо-восточный (длиной 125 м) и юго-восточный (длиной 380 м), которые вдаются в Щукинский полуостров и, вероятно, имеют старичную природу (продолжением бывшей старицы является Чистый залив).

                    Некогда имел два маленьких островка в восточной части. Поскольку вода поступает в данное водохранилище, в основном, из Волги (по каналу им. Москвы), Строгинский залив относительно чист.

                    Используется для околоводного отдыха и занятий водными видами спорта (водные лыжи и др.), а также для любительского лова рыбы (особенно в зимнее время) и купания, хотя в настоящее время купание официально запрещено.

                    Затон (залив) называется Строгинским по бывшему селу Строгино; Большим Строгинским - в противоположность небольшому заливу севернее данного, а Троице-Лыковской поймой по селу Троице-Лыкову - одному из немногих, сохранившихся на территории Москвы, расположенной к югу от него.
                """.trimIndent()
                ),
                "qgis_sibay_career" to WebMapInfo(
                    assetPath = "qgis_sibay_career",
                    imageUrl = "https://gis-directory.herokuapp.com/images/qgis_sibay_career.png",
                    description = """
                   Интересна предыстория открытия месторождения. Впервые на необычную породу обратил внимание охотник из деревни Старый Сибай. В поисках добычи он раскапывал нору куницы и обнаружил необычно тяжелую красную глину с кристаллами пирита. Узнавший об этом житель деревни Новый Сибай Амир Худайбердин в конце 1912 года отобрал несколько мешков этой глины и отвез их на анализ ученым. Исследования показали наличие в глине железняка с примесями золота и серебра. Геологи приступили к разведке, и важное открытие не заставило себя долго ждать. В 1915 году была пробита разведочная шахта глубиной 42 метра. Результат превзошел все ожидания. Месторождение оказалось на редкость богатым.В Сибайском карьере и в наши дни добывают медно-цинковый и медно-серный колчедан. В настоящее время добыча производится шахтным способом. Штольни шахт темнеют в стенках карьера.Карьер – главная туристическая достопримечательность города. Также Сибай известен добычей редкого поделочного камня долерита. Численность населения Сибая – 63 тысячи человек.
                """.trimIndent()
                ),
                "qgis_asbest_career" to WebMapInfo(
                    assetPath = "qgis_asbest_career",
                    imageUrl = "https://gis-directory.herokuapp.com/images/qgis_asbest_career.jpeg",
                    description = """
                    Главная достопримечательность города Асбест впечатляет каждого туриста своими колоссальными размерами. Карьер занимает гораздо большую площадь, чем сам город. Асбестовский карьер — один из крупнейших карьеров в России, который видно даже с самолета.

                    Общая протяженность Асбестовского карьера — 11 км, ширина — около 2,5 км, глубина — около 350 м.

                    На сегодняшний день добыча минерала в Асбесте составляет 25% мирового и 50% российского объёма. Сфера его применения достаточно обширна — минерал используют в ракетостроении, для производства долговечных строительных материалов, для создания ткани костюмов для пожарных, рукавиц для сталеваров, а также для теплоизоляции труб и многого другого.

                    Месторождение хризотил-асбеста на территории нынешнего города Асбеста было открыто в XIX веке, в феврале 1885 года, и было названо Баженовским, по названию ближайшей железнодорожной станции. Для выработки породы на территории карьера ведутся взрывные работы, на деревьях рядом со смотровой площадкой можно заметить таблички, предупреждающие о взрывных действиях в карьере.

                    Изначально на этой территории было два карьера — Центральный и Южный, а по середине между ними ходил общественный транспорт. Со временем карьеры росли и соединились в один большой.
                """.trimIndent()
                ),
                "qgis_borodino_incision" to WebMapInfo(
                    assetPath = "qgis_borodino_incision",
                    imageUrl = "https://gis-directory.herokuapp.com/images/qgis_borodino_incision.jpeg",
                    description = """
                    Самый крупный угольный разрез в России находится в Красноярском крае, в 7 км от города Бородино.  

                    Разрез в длину составляет 7 км, в ширину 2 км — это как 2000 футбольных полей! Добыча угля на разрезе ведётся круглый год, пик приходится на зимнее время — объём отгрузки доходит до тысячи вагонов в сутки. Всего предприятие добывает около 20 млн тонн угля в год. Если вагоны с углём, который добыли на Бородинском разрезе за всю историю, выстроить в одну линию, можно обогнуть экватор 8 раз!

                    Строительство разреза началось в 1945 г., а ввод в эксплуатацию состоялся в 1949 г. Промышленная добыча угля началась в 1950 г. Производственная мощность разреза в то время составила 1 млн т угля в год. В 1953 г. был закончен монтаж и принят в эксплуатацию первый шагающий экскаватор ЭШ-1 с вместимостью ковша 4 куб. м и длиной стрелы 40 м.
                """.trimIndent()
                ),
                "qgis_mikhaylovskiy_mine" to WebMapInfo(
                    assetPath = "qgis_mikhaylovskiy_mine",
                    imageUrl = "https://gis-directory.herokuapp.com/images/qgis_mikhaylovskiy_mine.jpeg",
                    description = """
                    Михайловский рудник находится в северо-западной части Курской области. Этот регион называют Курская Магнитная аномалия. Здесь стрелка компаса работает с отклонениями, так как в земле находятся миллиарды тонн железной руды. Причем это не просто порода, в некоторых местах содержание железа достигает 90%. Это самый большой железнорудный бассейн в мире. 

                    Недалеко от города Железногорск есть смотровая площадка, куда можно приехать и посмотреть на гигантские масштабы Михайловского рудника. Он принадлежат компании Металлоинвест. Руководство компании приняло решение о развитии промышленного туризма в Курской Магнитной аномалии. Здесь можно не только приехать на смотровую площадку Михайловского рудника, но и записаться на экскурсию на карьер или на завод по производству железной руды.  
                """.trimIndent()
                ),

                "qgis_lebedinskoe_gok" to WebMapInfo(
                    assetPath = "qgis_lebedinskoe_gok",
                    imageUrl = "https://gis-directory.herokuapp.com/images/qgis_lebedinskoe_gok.jpeg",
                    description = """
                    Лебединский горно-обогатительный комбинат (Лебединский ГОК, ЛГОК) — самое крупное российское предприятие по добыче и обогащению железной руды. Карьер предприятия, расположенный в городе Губкине Белгородской области, — крупнейший в мире.

                    Лебединский ГОК — единственный в России и СНГ производитель горячебрекетированного железа (ГБЖ). Кроме этого, комбинат производит железорудный концентрат, офлюсованные и неофлюсованные окатыши.

                    История предприятия началась в 1967 году, когда Правительство СССР приняло постановление о строительстве в Губкине горно-обогатительного комбината на базе Лебединского месторождения железных руд Курской магнитной аномалии. В начале 70-х годов в эксплуатацию ввели Лебединский кварцитный карьер, а в 1972 году ГОК выпустил первую партию продукции — железорудный концентрат. Вскоре была запущена и фабрика комкования. Сейчас ЛГОК работает в составе металлургического холдинга «Металлоинвест».
                """.trimIndent()
                ),
                "qgis_udachniy_mine" to WebMapInfo(
                    assetPath = "qgis_udachniy_mine",
                    imageUrl = "https://gis-directory.herokuapp.com/images/qgis_udachniy_mine.jpeg",
                    description = """
                    Удачнинский горно-обогатительный комбинат (ГОК) основан в 1979 году. Это градообразующее предприятие города Удачный, который расположен в 15 км от Полярного круга. В состав комбината входят горные, обогатительные, транспортные, ремонтные и вспомогательные подразделения. Удачнинский ГОК осуществляет добычу алмазов на объекте "Подземный рудник «Удачный» им. Ф. Б. Андреева"
                """.trimIndent()
                )
            )
        )
    }

}