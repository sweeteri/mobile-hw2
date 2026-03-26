package com.sweeteri.stepikclient.data.repository


import com.sweeteri.stepikclient.AppPreferences
import com.sweeteri.stepikclient.data.local.CourseDao
import kotlin.coroutines.cancellation.CancellationException

class ProfileRepositoryImpl(
    private val prefs: AppPreferences,
    private val courseDao: CourseDao
) : ProfileRepository {

    override suspend fun logout(): Result<Unit> {
        return try {
            prefs.clearToken()
            courseDao.clear()
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Result.failure(e)
        }
    }
}