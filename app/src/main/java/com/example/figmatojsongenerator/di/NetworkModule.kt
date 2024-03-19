package com.example.figmatojsongenerator.di

import com.example.figmatojsongenerator.BuildConfig
import com.example.figmatojsongenerator.data.local.PrefrenceManager
import com.example.figmatojsongenerator.data.remote.FigmaRestApi
import com.example.figmatojsongenerator.data.remote.interceptors.AuthHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authHeaderInterceptor: AuthHeaderInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authHeaderInterceptor)
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFigmaRestApi(retrofit: Retrofit) : FigmaRestApi {
        return retrofit.create(FigmaRestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthHeaderInterceptor(preferencesManager: PrefrenceManager) =
        AuthHeaderInterceptor(preferencesManager = preferencesManager)
}