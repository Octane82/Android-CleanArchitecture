package com.everlapp.cleanarch.core.extension

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.everlapp.cleanarch.core.platform.BaseActivity
import com.everlapp.cleanarch.core.platform.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*



inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun BaseFragment.close() = fragmentManager?.popBackStack()

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer

val BaseFragment.appContext: Context get() = activity?.applicationContext!!



/**
 * Start activity with put Extra
 */
inline fun <reified T : Activity> Activity.navigate(key: String = "", extra : String = "") {
    val intent = Intent(this, T::class.java)
    intent.putExtra(key, extra)
    startActivity(intent)
}

/**
 * Find fragment by TAG
 */
fun FragmentActivity.findFragmentByTagExt(fragmentTag: String) : Boolean =
        this.supportFragmentManager.findFragmentByTag(fragmentTag) != null



/**
 * Add fragment with backStack
 */
fun FragmentActivity.setFragment(fragment : Fragment,
                                 containerId : Int = android.R.id.content,
                                 backStackName: String?) =
        supportFragmentManager
                .beginTransaction()
                .add(containerId, fragment)
                .addToBackStack(backStackName)
                .commit()



/**
* Add fragment without backStack
*/
fun FragmentActivity.setFragment(fragment : Fragment,
                                 containerId : Int = android.R.id.content) =
        supportFragmentManager
                .beginTransaction()
                .add(containerId, fragment)
                .commit()


/**
 * Replace fragment with backStack
 */
fun FragmentActivity.replaceFragment(fragment: Fragment ,
                                     containerId: Int = android.R.id.content,
                                     backStackName: String?,
                                     fragmentTag: String = "") =
        supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment, fragmentTag)
                .addToBackStack(backStackName)
                .commit()

/**
 * Replace fragment without backStack
 */
fun FragmentActivity.replaceFragment(fragment: Fragment ,
                                     containerId: Int = android.R.id.content) =
        supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment)
                .commit()


/**
 * !!! If BUG with (like - don't exec after onSavedInstanceState())
 */
fun FragmentActivity.replaceFragmentAllowingStateLoss(fragment: Fragment ,
                                                      containerId: Int = android.R.id.content,
                                                      backStackName: String?,
                                                      fragmentTag: String = "") =
        supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment, fragmentTag)
                .addToBackStack(backStackName)
                .commitAllowingStateLoss()


