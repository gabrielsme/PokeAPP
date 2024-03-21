package com.gabrielmedeiros.pokeapp.data.model

abstract class BaseApiResponse<T> {
    open val count: Int = 0
    open val next: String = ""
    open val previous: String = ""
    val results: List<T> = listOf()
}