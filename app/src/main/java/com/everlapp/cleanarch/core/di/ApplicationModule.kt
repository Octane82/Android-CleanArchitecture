package com.everlapp.cleanarch.core.di

import android.content.Context
import com.everlapp.cleanarch.App
import com.everlapp.cleanarch.BuildConfig
import com.everlapp.cleanarch.core.data.db.DbRoom
import com.everlapp.cleanarch.features.movies.data.MoviesRepository
import com.everlapp.cleanarch.features.tasks.data.TasksRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {

    @Provides
    @Singleton
    fun provideApplicationContext() : Context = application


    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/")
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideMoviesRepository(dataSource: MoviesRepository.Network) : MoviesRepository = dataSource



    // ----------- Test ----------------------------------------------------------------------------

    @Provides
    @Singleton
    fun provideRoomDb() : DbRoom? = DbRoom.getInstance(application)


    @Provides
    @Singleton
    fun provideTasksRepository(dataSource: TasksRepository.Database) : TasksRepository = dataSource


}