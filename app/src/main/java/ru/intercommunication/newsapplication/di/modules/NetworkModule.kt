package ru.intercommunication.newsapplication.di.modules

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.intercommunication.newsapplication.core.api.services.NewsService
import ru.intercommunication.newsapplication.core.di.ApplicationContext
import ru.intercommunication.newsapplication.di.ApplicationScope
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalSerializationApi::class)
@Module
class NetworkModule {

    companion object {
        private const val CONNECT_TIME = 5000L
        private const val WRITE_TIME = 5000L
        private const val READE_TIME = 5000L

        const val BASE_URL = "https://newsapi.org/"
    }

    private val json = Json { ignoreUnknownKeys = true }
    private val cacheSize = (5 * 1024 * 1024).toLong()

    @[Provides ApplicationScope]
    fun provideCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, cacheSize)

    @[Provides ApplicationScope]
    fun provideOkHttpClient(
        cache: Cache,
    ): OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
        .readTimeout(READE_TIME, TimeUnit.SECONDS)
        .build()


    @[Provides ApplicationScope]
    fun provideNewsRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()


    @[Provides ApplicationScope]
    fun provideNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)
}