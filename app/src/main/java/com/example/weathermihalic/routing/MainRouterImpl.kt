package com.example.weathermihalic.routing

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.example.navigation.main.MainRouter
import com.example.search.ui.search.SearchFragment
import com.example.weathermihalic.R

@IdRes
private const val MAIN_CONTAINER = R.id.main_container

private const val APP_YOUTUBE_URI = "vnd.youtube:"
private const val WEB_YOUTUBE_URI = "http://www.youtube.com/watch?v="

class MainRouterImpl(
    private val activity: Activity,
    private val fragmentManager: FragmentManager
) : MainRouter {

    override fun showYoutube(query: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(APP_YOUTUBE_URI + query))
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(WEB_YOUTUBE_URI + query))

        try {
            activity.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            activity.startActivity(webIntent)
        }
    }

    override fun showMainScreen() {
        if (fragmentManager.findFragmentByTag(SearchFragment.TAG) == null) {
            fragmentManager.beginTransaction()
                .add(MAIN_CONTAINER, SearchFragment.newInstance(), SearchFragment.TAG)
                .commitNow()
            return
        }
    }
}
