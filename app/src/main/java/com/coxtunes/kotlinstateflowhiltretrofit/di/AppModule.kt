package com.coxtunes.kotlinstateflowhiltretrofit.di

import com.coxtunes.ponnobeponi.kotlinstateflowhiltretrofit.BaseConstant
import com.coxtunes.kotlinstateflowhiltretrofit.network.APIInterface
import com.coxtunes.kotlinstateflowhiltretrofit.repository.DefaultMainRepository
import com.coxtunes.kotlinstateflowhiltretrofit.repository.MainRepository
import com.coxtunes.kotlinstateflowhiltretrofit.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiInterface(httpClient: OkHttpClient) : APIInterface = Retrofit.Builder()
        .baseUrl(BaseConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
        .create(APIInterface::class.java)


    @Singleton
    @Provides
    fun provideLogInterceptor(): OkHttpClient {
        val addInterceptor = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        return addInterceptor.build()
    }

    @Singleton
    @Provides
    fun provideMainRepository(api: APIInterface): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }



}