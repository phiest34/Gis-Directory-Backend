package com.example.register

import com.example.models.WebMap

val webMaps: List<WebMap>
    get() = webMapsMutable.toList()

internal fun registerWebMaps() = register {
    webMap {
        assetName { "qgis_krasnogorsk_forest_zones" }
        name { "Лесные зоны Красногорска" }
        description {
            """
            Слои:
                1) Говно
                2) Моча
                3) Жопа
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