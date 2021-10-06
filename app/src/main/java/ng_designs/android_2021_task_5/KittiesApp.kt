package ng_designs.android_2021_task_5

import android.app.Application
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.util.CoilUtils
import ng_designs.android_2021_task_5.data.cats.remote.api.CatsApiImpl
import ng_designs.android_2021_task_5.data.cats.repository.CatsRepository
import ng_designs.android_2021_task_5.domain.common.utils.ServiceLocator
import ng_designs.android_2021_task_5.domain.common.utils.locate
import okhttp3.OkHttpClient

class KittiesApp: Application(), ImageLoaderFactory {
    private val locator = ServiceLocator

    override fun onCreate() {
        super.onCreate()

        with(locator){
            register<Context>(this@KittiesApp)
//            register(CatsDatabase.getDatabase(locate()))
            register(CatsRepository())
            register(CatsApiImpl)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this))
                    .build()
            }
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(locate()))
                } else {
                    add(GifDecoder())
                }
            }
            .build()
    }
}

