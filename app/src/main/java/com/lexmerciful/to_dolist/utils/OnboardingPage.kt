package com.lexmerciful.to_dolist.utils

import androidx.annotation.DrawableRes
import com.lexmerciful.to_dolist.R

sealed class OnboardingPage(
    @DrawableRes
    val image:Int,
    @DrawableRes
    val title: Int,
    val description: String
) {

    object First: OnboardingPage(
        image = R.drawable.first_onboarding,
        title = R.drawable.first_onboard_smart_task,
        description = "Easily add, edit, and prioritize tasks. Efficiently manage tasks with \n" +
                "our smart system. Quickly add, edit, and prioritize tasks, ensuring \n" +
                "you stay organized and focused on what matters most."
    )

    object Second: OnboardingPage(
        image = R.drawable.second_onboarding,
        title = R.drawable.second_onboard_reminder_notifications,
        description = "Set reminders to stay on top of your tasks. Never miss a beat with \n" +
                "reminder notifications. Stay on track by setting timely reminders \n" +
                "for you tasks ensuring you accomplish them on time and without stress."
    )

    object Third: OnboardingPage(
        image = R.drawable.third_onboarding,
        title = R.drawable.third_onboard_seamless_collaboration,
        description = "Share lists and collaborate with others. Enhance teamwork with \n" +
                "seamless collaboration. Share task lists with colleagues, friends, or \n" +
                "family. Allow everyone to contribute, update, and work together towards \n" +
                "common goals effortlessly."
    )

}