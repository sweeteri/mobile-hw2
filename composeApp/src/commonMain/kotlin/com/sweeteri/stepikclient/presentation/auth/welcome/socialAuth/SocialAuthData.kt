package com.sweeteri.stepikclient.presentation.auth.welcome.socialAuth

import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.ic_apple
import com.sweeteri.stepikclient.generated.resources.ic_github
import com.sweeteri.stepikclient.generated.resources.ic_google
import com.sweeteri.stepikclient.generated.resources.ic_twitter
import com.sweeteri.stepikclient.generated.resources.ic_vk
import com.sweeteri.stepikclient.presentation.auth.welcome.SocialAuth

val firstRowSocials = listOf(
    SocialAuth(Res.drawable.ic_apple, "Apple"),
    SocialAuth(Res.drawable.ic_vk, "VK"),
    SocialAuth(Res.drawable.ic_google, "Google")
)

val secondRowSocials = listOf(
    SocialAuth(Res.drawable.ic_twitter, "Twitter"),
    SocialAuth(Res.drawable.ic_github, "GitHub")
)