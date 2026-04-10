package com.sweeteri.stepikclient.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.sweeteri.stepikclient.AppPreferences
import com.sweeteri.stepikclient.data.local.AppDatabase
import com.sweeteri.stepikclient.data.local.AppPreferencesImpl
import com.sweeteri.stepikclient.data.remote.api.StepikApiClient
import com.sweeteri.stepikclient.data.repository.AuthRepository
import com.sweeteri.stepikclient.data.repository.AuthRepositoryImpl
import com.sweeteri.stepikclient.data.repository.CourseDetailRepository
import com.sweeteri.stepikclient.data.repository.CourseDetailRepositoryImpl
import com.sweeteri.stepikclient.data.repository.CoursesRepository
import com.sweeteri.stepikclient.data.repository.CoursesRepositoryImpl
import com.sweeteri.stepikclient.data.repository.LoginRepository
import com.sweeteri.stepikclient.data.repository.LoginRepositoryImpl
import com.sweeteri.stepikclient.data.repository.ProfileRepository
import com.sweeteri.stepikclient.data.repository.ProfileRepositoryImpl
import com.sweeteri.stepikclient.domain.usecase.EnrollCourseUseCase
import com.sweeteri.stepikclient.domain.usecase.GetCourseDetailUseCase
import com.sweeteri.stepikclient.domain.usecase.GetCourseReviewsUseCase
import com.sweeteri.stepikclient.domain.usecase.GetCoursesUseCase
import com.sweeteri.stepikclient.domain.usecase.LoginUseCase
import com.sweeteri.stepikclient.domain.usecase.LogoutUseCase
import com.sweeteri.stepikclient.domain.usecase.SetOnboardingShownUseCase
import com.sweeteri.stepikclient.presentation.auth.login.LoginViewModel
import com.sweeteri.stepikclient.presentation.course.CourseDetailViewModel
import com.sweeteri.stepikclient.presentation.main.MainViewModel
import com.sweeteri.stepikclient.presentation.onboarding.OnboardingViewModel
import com.sweeteri.stepikclient.presentation.profile.ProfileViewModel
import com.sweeteri.stepikclient.presentation.search.SearchViewModel
import com.sweeteri.stepikclient.presentation.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create {
            get<Context>().filesDir.resolve("app.preferences_pb")
        }
    }

    single<AppPreferences> { AppPreferencesImpl(get()) }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    single { get<AppDatabase>().courseDao() }
    single { StepikApiClient() }

    single<CoursesRepository> { CoursesRepositoryImpl(get(), get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<CourseDetailRepository> { CourseDetailRepositoryImpl(get()) }

    single { GetCoursesUseCase(get()) }
    single { LoginUseCase(get()) }
    single { LogoutUseCase(get()) }
    single { SetOnboardingShownUseCase(get()) }
    single { GetCourseDetailUseCase(get()) }
    single { EnrollCourseUseCase(get()) }
    single { GetCourseReviewsUseCase(get()) }


    viewModel { MainViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { StartViewModel(get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { CourseDetailViewModel(get(), get(), get()) }
}
