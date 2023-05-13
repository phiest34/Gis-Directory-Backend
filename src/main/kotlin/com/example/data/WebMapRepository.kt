package com.example.data

import com.example.firebase.WebmapsFireBaseStorage
import com.example.models.WebMap
import com.example.models.WebMapInfo
import com.example.register.WebMapInfoStaticStorage
import com.example.register.webMaps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WebMapRepository {
    suspend fun getWebMaps(): List<WebMap> {
        val result = webMaps.toMutableList()

        val remoteWebMaps = withContext(Dispatchers.IO) {
            WebmapsFireBaseStorage.loadWebmapsAsync().map { it.toWebMap() }
        }

        remoteWebMaps.onEach { fireBaseWebMap ->
            val index = result.indexOfFirst { it.assetPath == fireBaseWebMap.assetPath }
            if (index == -1) {
                result.add(fireBaseWebMap)
            } else {
                result[index] = fireBaseWebMap
            }
        }
        return result
    }

    suspend fun getWebMapInfo(assetPath: String, staticStorage: WebMapInfoStaticStorage): WebMapInfo? =
        staticStorage[assetPath] ?: withContext(Dispatchers.IO) {
            WebmapsFireBaseStorage.loadWebmapsAsync().map { it.toWebMapInfo() }.find { it.assetPath == assetPath }
        }
}