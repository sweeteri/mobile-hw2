package com.sweeteri.core.core.pagination

interface Paginator<Key, Item> {
    suspend fun loadNext()
    fun reset()
}
