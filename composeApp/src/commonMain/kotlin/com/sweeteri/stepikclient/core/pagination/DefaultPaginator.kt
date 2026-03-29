package com.sweeteri.stepikclient.core.pagination

interface Paginator<Key, Item> {
    suspend fun loadNext()
    fun reset()
}
