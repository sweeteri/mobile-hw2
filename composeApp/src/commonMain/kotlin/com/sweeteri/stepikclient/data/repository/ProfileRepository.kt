package com.sweeteri.stepikclient.data.repository


interface ProfileRepository {
    suspend fun logout(): Result<Unit>
}