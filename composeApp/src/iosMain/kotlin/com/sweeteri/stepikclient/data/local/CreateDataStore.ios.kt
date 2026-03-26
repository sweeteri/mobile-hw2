package com.sweeteri.stepikclient.data.local

fun createDataStore(): DataStore<Preferences> =
    createDataStore(
        producePath = {
            val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(documentDirectory).path + "/$DATASTORE_FILE_NAME"
        }
    )