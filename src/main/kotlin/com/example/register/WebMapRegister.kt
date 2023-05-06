package com.example.register

import com.example.models.WebMap

val webMaps: List<WebMap>
    get() = webMapsMutable.toList()

internal fun registerWebMaps() = register {
    webMap {
        assetName { "qgis_strogyno_forest_zone" }
        name { "Лесные зоны района Строгино" }
        description {
            """
            Слои:
                1) Говно
                2) Моча
                3) Жопа
        """.trimIndent()
        }
    }

    webMap {
        assetName { "qgis_sibay_career" }
        name { "Сибайский карьер" }
        description {
            """
            Итересная информация о сибайском карьере из википедии
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