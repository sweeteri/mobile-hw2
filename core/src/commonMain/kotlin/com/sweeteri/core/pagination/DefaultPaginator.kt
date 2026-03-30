package com.sweeteri.core.pagination

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (Key, String) -> Result<List<Item>>,
    private val getNextKey: (Key, List<Item>) -> Key,
    private val onError: (Throwable?) -> Unit,
    private val onSuccess: (List<Item>, Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    private var endReached = false
    private var lastQuery: String = ""

    override suspend fun loadNext() {
        loadNext(lastQuery)
    }

    suspend fun loadNext(query: String) {
        if (isMakingRequest || endReached) return

        lastQuery = query
        isMakingRequest = true
        onLoadUpdated(true)

        val result = onRequest(currentKey, query)

        result.fold(
            onSuccess = { items ->
                if (items.isEmpty()) endReached = true
                currentKey = getNextKey(currentKey, items)
                onSuccess(items, currentKey)
            },
            onFailure = { error ->
                onError(error)
            }
        )

        isMakingRequest = false
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
        endReached = false
    }
}
