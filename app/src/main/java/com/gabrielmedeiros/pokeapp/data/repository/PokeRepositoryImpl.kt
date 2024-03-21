package com.gabrielmedeiros.pokeapp.data.repository

import com.gabrielmedeiros.pokeapp.data.source.ApiRemoteDataSource

class PokeRepositoryImpl(
    private val apiRemoteDataSource: ApiRemoteDataSource
) : PokeRepository {
}