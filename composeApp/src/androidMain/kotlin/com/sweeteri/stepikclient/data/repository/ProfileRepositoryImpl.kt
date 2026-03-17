package com.sweeteri.stepikclient.data.repository


import com.sweeteri.stepikclient.AppPreferences
import com.sweeteri.stepikclient.data.local.CourseDao

class ProfileRepositoryImpl(
    private val prefs: AppPreferences,
    private val courseDao: CourseDao
) : ProfileRepository {

    override suspend fun logout() {
        prefs.clearToken()
        courseDao.clear()
    }
}