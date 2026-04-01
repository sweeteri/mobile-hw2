package com.sweeteri.core.pagination

interface Paginator<Key, Item> {
    suspend fun loadNext()
    fun reset()
}
