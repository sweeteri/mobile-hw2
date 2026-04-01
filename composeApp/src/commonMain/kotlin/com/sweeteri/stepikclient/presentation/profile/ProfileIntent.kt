package com.sweeteri.stepikclient.presentation.profile

sealed interface ProfileIntent {
    object Logout : ProfileIntent
}