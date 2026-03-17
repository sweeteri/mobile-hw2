package com.sweeteri.stepikclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.sweeteri.stepikclient.data.local.AppDatabase
import com.sweeteri.stepikclient.data.repository.CoursesRepositoryImpl
import com.sweeteri.stepikclient.data.repository.ProfileRepositoryImpl
import com.sweeteri.stepikclient.utils.AppContextHolder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        AppContextHolder.context = applicationContext

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_db"
        ).build()

        val coursesRepository = CoursesRepositoryImpl(db.courseDao())

        val prefs = createAppPreferences()

        val profileRepository = ProfileRepositoryImpl(
            prefs = prefs,
            courseDao = db.courseDao()
        )

        setContent {
            App(
                coursesRepository = coursesRepository,
                profileRepository = profileRepository
            )
        }
    }
}
