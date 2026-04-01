package com.sweeteri.stepikclient.presentation.onboarding


import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.onboarding_desc_certificates
import com.sweeteri.stepikclient.generated.resources.onboarding_desc_courses
import com.sweeteri.stepikclient.generated.resources.onboarding_desc_platform
import com.sweeteri.stepikclient.generated.resources.onboarding_desc_stepik
import com.sweeteri.stepikclient.generated.resources.onboarding_title_certificates
import com.sweeteri.stepikclient.generated.resources.onboarding_title_courses
import com.sweeteri.stepikclient.generated.resources.onboarding_title_platform
import com.sweeteri.stepikclient.generated.resources.onboarding_title_stepik
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class OnboardingPage(
    val titleRes: StringResource,
    val descriptionRes: StringResource,
    val imageRes: DrawableResource? = null // возможно картинки потом будут
) {
    data object Stepik : OnboardingPage(
        titleRes = Res.string.onboarding_title_stepik,
        descriptionRes = Res.string.onboarding_desc_stepik
    )

    data object Platform : OnboardingPage(
        titleRes = Res.string.onboarding_title_platform,
        descriptionRes = Res.string.onboarding_desc_platform
    )

    data object Courses : OnboardingPage(
        titleRes = Res.string.onboarding_title_courses,
        descriptionRes = Res.string.onboarding_desc_courses
    )

    data object Certificates : OnboardingPage(
        titleRes = Res.string.onboarding_title_certificates,
        descriptionRes = Res.string.onboarding_desc_certificates
    )
}