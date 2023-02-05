package com.github.flinkou.core_network.di

import com.github.flinkou.core_network.BuildConfig
import com.github.flinkou.core_network.api.KinopoiskApi
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {

  fun api():KinopoiskApi

}

@Module
abstract class NetworkModule {
    companion object{
        private const val base_url = "https://kinopoiskapiunofficial.tech/"
        private const val token = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

        @Provides
        @Singleton
        fun provideApi(): KinopoiskApi =
            Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val originalRequest = chain.request()
                            val request = originalRequest.newBuilder()
                                .header("X-API-KEY", token).build()
                            chain.proceed(request)
                        }
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level =
                                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                else HttpLoggingInterceptor.Level.NONE
                        })
                        .build()
                )
                .build()
                .create(KinopoiskApi::class.java)
    }
}