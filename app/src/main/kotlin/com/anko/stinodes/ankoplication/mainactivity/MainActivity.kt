package com.anko.stinodes.ankoplication.mainactivity

import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.mainactivity.MainActivity.FragmentView.Detail
import com.anko.stinodes.ankoplication.mainactivity.MainActivity.FragmentView.Home
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment
import com.anko.stinodes.ankoplication.mainactivity.homefragment.HomeFragment
import com.anko.stinodes.ankoplication.mainactivity.toolbar.loginfragment.ToolbarLoginFragment
import com.anko.stinodes.ankoplication.mainactivity.toolbar.toolbarimagefragment.ToolbarImageFragment
import com.anko.stinodes.ankoplication.mainactivity.ui.MainActivityUI
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    val ui = MainActivityUI()

    enum class FragmentView {
        Home,
        Detail,
        Anime
    }
    lateinit var currentView: FragmentView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui.setContentView(this)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        handleAppbarScrolling(ui.appBar)

        navigate(Home)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun handleAppbarScrolling(appBar: AppBarLayout) {
        /**
         * Listens for scrolling and fires when appbarlayout offset changes
         * -> change opacity of widgets in appbarlayout
         */
        appBar.addOnOffsetChangedListener {
            appBarLayout, verticalOffset ->
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                val toolbarHeight = toolbar.measuredHeight.toFloat()
                val appBarHeight = appBarLayout.measuredHeightAndState.toFloat()
                val f = (verticalOffset / (appBarHeight - toolbarHeight * 2)) * 255
                val opacity = Math.max(Math.round(Math.max(255 + f, 0.1f)), 0)
                ui.appBarBackground.imageAlpha = opacity
                ui.toolbarFragmentContainer.alpha = (opacity.toFloat() / 255)

//                if (ui.appBarImageVisible)
//                    ui.appBarImage.imageAlpha = opacity

                if (ui.toolbarFragmentContainer.visibility == View.VISIBLE && opacity <= 0)
                    ui.toolbarFragmentContainer.visibility = View.GONE
                else if (ui.toolbarFragmentContainer.visibility == View.GONE && opacity > 0)
                    ui.toolbarFragmentContainer.visibility = View.VISIBLE

            }
        }
    }

    fun navigate(fragment: FragmentView, bundle: Bundle = Bundle()) {
        val f = getFragment(fragment, bundle)
        val tbf = getToolbarFragment(fragment, bundle)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, f)
                .addToBackStack(null)
                .commit()
    }
    fun setToolbarFragment(fragment: Fragment?) {
        if (fragment != null)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.toolbarViewContainer, fragment)
                    .commit()
        else
            if (ui.toolbarFragmentContainer.childCount != 0)
                supportFragmentManager.beginTransaction()
                        .remove(
                                supportFragmentManager
                                        .findFragmentById(R.id.toolbarViewContainer)
                        )
                        .commit()
    }

    fun getFragment(view: FragmentView, bundle: Bundle = Bundle()) = when(view) {
        (Home)-> HomeFragment.create(bundle)
        (Detail)-> DetailFragment.create(bundle)
        else -> HomeFragment.create(bundle)
    }
    fun getToolbarFragment(view: FragmentView, bundle: Bundle = Bundle()) = when(view) {
        (Home)-> ToolbarLoginFragment()
        (Detail)-> ToolbarImageFragment.create(bundle)
        else -> ToolbarLoginFragment()
    }
}
