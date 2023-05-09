package com.example.register

import com.example.models.WebMap

val webMaps: List<WebMap>
    get() = webMapsMutable.toList()

internal fun registerWebMaps() = register {
    webMap {
        assetName { "qgis_strogyno_bay" }
        name { "Строгинский залив" }
        description {
            """
            Строги́нская по́йма (Строги́нский затон, Строги́нский залив, Тро́ице-Лы́ковская пойма) — зелёная зона площадью 13,2 тысячи квадратных метров на правом берегу залива Москвы-реки в районе в Строгино Северо-Западного административного округа Москвы. Имеет статус особо охраняемой природной территории как составная часть парка «Москворецкий.
        """.trimIndent()
        }
    }

    webMap {
        assetName { "qgis_sibay_career" }
        name { "Сибайский карьер" }
        description {
            """
            Находящийся в Башкирии Сибайский карьер – один из самых глубоких в мире. Его глубина – более 500 метров, а диаметр карьера – 2 километра. Трудно представить весь масштаб этого огромнейшего карьера. Его глубина такова, что даже стоя на краю, не видно дна колоссальной воронки.
        """.trimIndent()
        }
    }

    webMap {
        assetName { "qgis_asbest_career" }
        name { "Асбестовский карьер" }
        description {
            """
            Асбестовский карьер — огромный карьер, в котором добывают одноименный минерал, который и дал название близлежащему городу. Асбестовский карьер является природной достопримечательностью не только Свердловской области, но и всей России. Асбест является волокнистым минералом, его название произошло от греческого слова «асбестос», которое означает «негорючий», что отлично описывает его физические свойства. Карьер в Асбесте — одно из самых крупных месторождений хризотил-асбеста во всем мире.
        """.trimIndent()
        }
    }

    webMap {
        assetName { "qgis_borodino_incision" }
        name { "Разрез Бородинский" }
        description {
            """
            У́гольный разре́з Бороди́нский — угледобывающее предприятие, расположенное в Красноярском крае России. Входит в состав Сибирской угольной энергетической компании (СУЭК).
        """.trimIndent()
        }
    }

    webMap {
        assetName { "qgis_lebedinskoe_gok" }
        name { "Лебединский горно-обогатительный комбинат" }
        description {
            """
            Лебединский ГОК является крупнейшим в России и СНГ предприятием по добыче и обогащению железной руды, производству высококачественного железорудного сырья и металлоресурсов.
        """.trimIndent()
        }
    }

    webMap {
        assetName { "qgis_udachniy_mine" }
        name { "Рудник имени Ф. Б. Андреева Удачный" }
        description {
            """
            Удачнинский горно-обогатительный комбинат (ГОК) основан в 1979 году. Это градообразующее предприятие города Удачный, который расположен в 15 км от Полярного круга. В состав комбината входят горные, обогатительные, транспортные, ремонтные и вспомогательные подразделения. Удачнинский ГОК осуществляет добычу алмазов на объекте "Подземный рудник «Удачный» им. Ф. Б. Андреева"
        """.trimIndent()
        }
    }
}


private fun register(build: WebMapRegisterContext.() -> Unit): List<WebMap> {
    val builder = WebMapRegisterContext().apply(build)
    return builder.webMaps.also { webMapsMutable = it }
}

private class WebMapBuilder {
    fun assetName(block: () -> String) {
        assetName = block.invoke()
    }

    fun name(block: () -> String) {
        name = block.invoke()
    }

    fun description(block: () -> String) {
        description = block.invoke()
    }

    private var assetName: String = ""
    private var name: String = ""
    private var description: String = ""

    fun build() = WebMap(assetName, name, description)
}

private class WebMapRegisterContext {
    private var list = mutableListOf<WebMap>()

    val webMaps: List<WebMap>
        get() = list.toList()

    fun webMap(webMapBuilder: WebMapBuilder.() -> Unit) {
        val webMap = WebMapBuilder().also { it.webMapBuilder() }.build()
        list.add(webMap)
    }
}

private var webMapsMutable: List<WebMap> = emptyList()