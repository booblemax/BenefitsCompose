package by.akella.benefits.util

import android.content.Context
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import okhttp3.OkHttpClient

class CoilInitializer(
    private val okHttpClient: OkHttpClient
) {

    fun init(context: Context) {
        val coilOkHttpClient = okHttpClient.newBuilder()
            .cache(CoilUtils.createDefaultCache(context))
            .build()
        Coil.setImageLoader(
            ImageLoader.Builder(context)
                .okHttpClient(coilOkHttpClient)
                .build()
        )
    }
}