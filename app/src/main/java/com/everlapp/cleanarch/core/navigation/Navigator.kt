package com.everlapp.cleanarch.core.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.FragmentActivity

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.everlapp.cleanarch.App
import com.everlapp.cleanarch.R
import com.everlapp.cleanarch.core.extension.empty
import com.everlapp.cleanarch.core.extension.replaceFragment
import com.everlapp.cleanarch.features.login.Authenticator
import com.everlapp.cleanarch.features.movies.view.MoviesActivity
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import com.everlapp.cleanarch.features.tasks.view.TaskDetailsFragment
import com.everlapp.cleanarch.features.tasks.view.TasksActivity
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) : LifecycleObserver {

    private lateinit var navigator: SupportAppNavigator
    private lateinit var router: Router

    /**
     * Init lifecycle events
     */
    fun init(activity: FragmentActivity?) {

        activity?.lifecycle?.addObserver(this)

        // TODO: 23.10.2018 - Cicerone until not support androidX - work with Jetifier
        navigator = SupportAppNavigator(activity, R.id.fragmentContainer)
        router = App.INSTANCE.getRouter()
    }



    // private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showTasks(context)              // TODO: By default -> TRUE
            //true -> showMovies(context)
            //false -> showLogin(context)
        }
    }

    private fun showTasks(context: Context) =
            context.startActivity(TasksActivity.callingIntent(context))

    /**
     * Show task detail fragment
     * naviExtras - task name
     */
    fun showTaskDetail(activity: FragmentActivity, task: TaskData, navigationExtras: Extras) {
        //activity.replaceFragment(TaskDetailsFragment(), R.id.fragmentContainer, null)

        router.navigateTo(Screens.TaskDetailsScreen())
    }





    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))

    /*fun showMovieDetails(activity: FragmentActivity, movie: MovieView, navigationExtras: Extras) {
        val intent = MovieDetailsActivity.callingIntent(activity, movie)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }*/

    private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
    private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

    fun openVideo(context: Context, videoUrl: String) {
        try {
            context.startActivity(createYoutubeIntent(videoUrl))
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
        }
    }

    private fun createYoutubeIntent(videoUrl: String): Intent {
        val videoId = when {
            videoUrl.startsWith(VIDEO_URL_HTTP) -> videoUrl.replace(VIDEO_URL_HTTP, String.empty())
            videoUrl.startsWith(VIDEO_URL_HTTPS) -> videoUrl.replace(VIDEO_URL_HTTPS, String.empty())
            else -> videoUrl
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        intent.putExtra("force_fullscreen", true)

        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        return intent
    }



    class Extras(val transitionSharedElement: View)


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Timber.d("ON-RESUME NAVI")
        App.INSTANCE.getNavigatorHolder().setNavigator(navigator)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Timber.d("ON-PAUSE NAVI")
        App.INSTANCE.getNavigatorHolder().removeNavigator()
    }

}