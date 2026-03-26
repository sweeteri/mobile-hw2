package com.sweeteri.stepikclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sweeteri.stepikclient.data.repository.AuthRepositoryImpl
import com.sweeteri.stepikclient.data.repository.CoursesRepositoryImpl
import com.sweeteri.stepikclient.data.repository.LoginRepositoryImpl
import com.sweeteri.stepikclient.data.repository.ProfileRepositoryImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val app = application as MyApplication

        val db = app.db
        val prefs = app.prefs
        val apiClient = app.apiClient
        val coursesRepository = CoursesRepositoryImpl(db.courseDao(), apiClient)

        val profileRepository = ProfileRepositoryImpl(
            prefs = prefs,
            courseDao = db.courseDao()
        )
        val loginRepository = LoginRepositoryImpl(prefs)
        val authRepository = AuthRepositoryImpl(prefs)
        setContent {
            App(
                coursesRepository = coursesRepository,
                profileRepository = profileRepository,
                authRepository = authRepository,
                loginRepository = loginRepository
            )
        }
    }
}
