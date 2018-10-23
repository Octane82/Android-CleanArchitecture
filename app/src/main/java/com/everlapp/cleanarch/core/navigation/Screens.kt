package com.everlapp.cleanarch.core.navigation

import com.everlapp.cleanarch.features.tasks.view.TaskDetailsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class TaskDetailsScreen : SupportAppScreen() {
        override fun getFragment() = TaskDetailsFragment()

    }

}