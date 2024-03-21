package com.gabrielmedeiros.pokeapp.di

import com.gabrielmedeiros.pokeapp.data.repository.PokeRepository
import com.gabrielmedeiros.pokeapp.data.repository.PokeRepositoryImpl
import com.gabrielmedeiros.pokeapp.data.source.ApiRemoteDataSource
import com.gabrielmedeiros.pokeapp.domain.usecase.GetPokemonListUseCase
import com.gabrielmedeiros.pokeapp.ui.main.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = ""

private val viewModelModule = module {
    viewModelOf(::MainViewModel)
}

private val domainModule = module {
    factoryOf(::GetPokemonListUseCase)
}

private val dataModule = module {
    singleOf(::PokeRepositoryImpl) { bind<PokeRepository>() }
}

private val networkModule = module {
    singleOf(::provideRemoteSource)
    singleOf(::provideRetrofit)
    singleOf(::provideOkHttpClient)
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
        .client(okHttpClient).build()
}

private fun provideRemoteSource(retrofit: Retrofit): ApiRemoteDataSource =
    retrofit.create(ApiRemoteDataSource::class.java)

val pokeAppModules = module {
    includes(
        viewModelModule,
        domainModule,
        dataModule,
        networkModule
    )
}