package com.sweeteri.stepikclient.presentation.profile

sealed interface ProfileEvent {
    object LoggedOut : ProfileEvent
}