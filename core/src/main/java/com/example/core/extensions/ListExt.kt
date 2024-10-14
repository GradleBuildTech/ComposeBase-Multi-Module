package com.example.core.extensions

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> List<*>.checkItemsAre(): List<T>? =
    if (all { it is T })
        this as List<T>
    else null