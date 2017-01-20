package com.anko.stinodes.ankoplication.mainactivity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.mainactivity.animefragment.AnimeFragment
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment
import com.anko.stinodes.ankoplication.mainactivity.homefragment.HomeFragment
import com.anko.stinodes.ankoplication.mainactivity.loginfragment.ToolbarLoginFragment
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

        val adapter = MainActivityFragmentAdapter(supportFragmentManager)
                .add(getFragment(FragmentView.Home), "Releases")
                .add(getFragment(FragmentView.Anime), "Anime")
        ui.viewPager.adapter = adapter
        ui.tabLayout.setupWithViewPager(ui.viewPager)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        /**
         * Listens for scrolling and fires when appbarlayout offset changes
         * -> change opacity of widgets in appbarlayout
         */
        app_bar.addOnOffsetChangedListener {
            appBarLayout, verticalOffset ->
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    val toolbarHeight = toolbar.measuredHeight.toFloat()
                    val appBarHeight = appBarLayout.measuredHeightAndState.toFloat()
                    val f = (verticalOffset / (appBarHeight - toolbarHeight * 2)) * 255
                    val opacity = Math.max(Math.round(Math.max(255 + f, 0.1f)), 0)
                    ui.appBarBackground.imageAlpha = opacity
                    ui.toolbarFragmentContainer.alpha = (opacity.toFloat() / 255)

                    if (ui.toolbarFragmentContainer.visibility == VISIBLE && opacity <= 0)
                        ui.toolbarFragmentContainer.visibility = GONE
                    else if (ui.toolbarFragmentContainer.visibility == GONE && opacity > 0)
                        ui.toolbarFragmentContainer.visibility = VISIBLE
                }
        }

        fragmentManager.beginTransaction()
                .add(R.id.toolbarViewContainer, ToolbarLoginFragment())
                .commit()
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

    fun getFragment(view: FragmentView, bundle: Bundle = Bundle()) = when(view) {
        (FragmentView.Home)-> HomeFragment.create(bundle)
        (FragmentView.Detail)-> DetailFragment.create(bundle)
        (FragmentView.Anime)-> AnimeFragment.create(bundle)
        else -> HomeFragment.create(bundle)
    }
}
