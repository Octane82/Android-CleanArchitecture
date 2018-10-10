package com.everlapp.cleanarch.core.platform

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.everlapp.cleanarch.AndroidApplication
import com.everlapp.cleanarch.core.di.ApplicationComponent
import com.everlapp.cleanarch.core.extension.appContext
import com.everlapp.cleanarch.core.extension.viewContainer
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

import com.everlapp.cleanarch.R.color
import com.everlapp.cleanarch.core.extension.inflate

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId(), container, false)*/

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = container?.inflate(layoutId())


    open fun onBackPressed() {}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
            with(activity) { if (this is BaseActivity) this.progress.visibility = viewStatus }

    internal fun notify(@StringRes message: Int) =
            Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { _ -> action.invoke() }
        snackBar.setActionTextColor(ContextCompat.getColor(appContext,
                color.colorTextPrimary))
        snackBar.show()
    }

}