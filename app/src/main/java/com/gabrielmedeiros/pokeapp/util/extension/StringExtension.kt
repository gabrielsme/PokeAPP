package com.gabrielmedeiros.pokeapp.util.extension

fun String.capitalize(): String {
    return this
        .split("\\W+".toRegex())
        .joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
}