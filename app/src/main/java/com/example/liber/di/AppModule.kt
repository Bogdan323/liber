package com.example.liber.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import com.example.liber.common.firebase.FirebaseCommon
import com.example.liber.data.remote.api.GamesApi
import com.example.liber.data.remote.repository.GamesRepositoryImpl
import com.example.liber.domain.repository.GamesRepository
import com.example.liber.common.Constants.BASE_URL
import com.example.liber.common.Constants.INTRODUCTION_SHARED_PREFERENCES
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestoreDatabase() = Firebase.firestore

    @Provides
    fun provideIntroductionSP(
        application: Application
    ) = application.getSharedPreferences(INTRODUCTION_SHARED_PREFERENCES, MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideFirebaseCommon(
        firebase: FirebaseFirestore,
        auth: FirebaseAuth
    ) = FirebaseCommon(firebase, auth)

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY }
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): GamesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GamesApi::class.java)
    }

    @Provides
    fun provideGamePagingSource(api: GamesApi): com.example.liber.data.remote.GamesPagingSource {
        return com.example.liber.data.remote.GamesPagingSource(api)
    }

    @Provides
    @Singleton
    fun provideRepository(api: GamesApi): GamesRepository = GamesRepositoryImpl(api)
}