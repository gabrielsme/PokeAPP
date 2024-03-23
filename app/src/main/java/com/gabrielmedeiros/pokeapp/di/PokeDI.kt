package com.gabrielmedeiros.pokeapp.di

import com.gabrielmedeiros.pokeapp.data.pagination.ListPokemonPagingSource
import com.gabrielmedeiros.pokeapp.data.repository.PokeRepository
import com.gabrielmedeiros.pokeapp.data.repository.PokeRepositoryImpl
import com.gabrielmedeiros.pokeapp.data.source.ApiRemoteDataSource
import com.gabrielmedeiros.pokeapp.domain.usecase.GetPokemonByUrlUseCase
import com.gabrielmedeiros.pokeapp.domain.usecase.GetPokemonListUseCase
import com.gabrielmedeiros.pokeapp.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val viewModelModule = module {
    viewModelOf(::MainViewModel)
}

private val domainModule = module {
    factoryOf(::GetPokemonListUseCase)
    factoryOf(::GetPokemonByUrlUseCase)
}

private val dataModule = module {
    singleOf(::PokeRepositoryImpl) { bind<PokeRepository>() }
    singleOf(::ListPokemonPagingSource)
}

private val networkModule = module {
    singleOf(::provideRemoteSource)
    singleOf(::provideRetrofit)
    singleOf(::provideOkHttpClient)
}

private val dispatchersKoinModule = module {
    single { Dispatchers.IO }
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
        networkModule,
        dispatchersKoinModule
    )
}