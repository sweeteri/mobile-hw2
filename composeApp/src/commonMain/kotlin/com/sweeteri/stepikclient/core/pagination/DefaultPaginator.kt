package com.sweeteri.stepikclient.core.pagination

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (Key) -> Result<List<Item>>,
    private val getNextKey: (Key, List<Item>) -> Key,
    private val onError: (Throwable?) -> Unit,
    private val onSuccess: (List<Item>, Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    private var endReached = false

    override suspend fun loadNext() {
        if (isMakingRequest || endReached) return

        isMakingRequest = true
        onLoadUpdated(true)

        val result = onRequest(currentKey)

        result.fold(
            onSuccess = { items ->
                if (items.isEmpty()) {
                    endReached = true
                }
                currentKey = getNextKey(currentKey, items)
                onSuccess(items, currentKey)
            },
            onFailure = {
                onError(it)
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
