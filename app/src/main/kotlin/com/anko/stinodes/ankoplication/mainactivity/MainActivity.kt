package com.anko.stinodes.ankoplication.mainactivity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.mainactivity.MainActivity.FragmentView.Detail
import com.anko.stinodes.ankoplication.mainactivity.MainActivity.FragmentView.Home
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment
import com.anko.stinodes.ankoplication.mainactivity.homefragment.HomeFragment
import com.anko.stinodes.ankoplication.mainactivity.toolbar.loginfragment.ToolbarLoginFragment
import com.anko.stinodes.ankoplication.mainactivity.toolbar.toolbarimagefragment.ToolbarImageFragment
import com.anko.stinodes.ankoplication.mainactivity.ui.MainActivityUI
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

        handleAppbarScrolling(ui.appBar)

        stepTo(Home)
    }

    fun handleAppbarScrolling(appBar: AppBarLayout) {
        /**
         * Listens for scrolling and fires when appbarlayout offset changes
         * -> change opacity of widgets in appbarlayout
         */
        appBar.addOnOffsetChangedListener {
            appBarLayout, verticalOffset ->

        }
    }

    fun navigate(fragment: FragmentView, bundle: Bundle = Bundle()) {
        val f = getFragment(fragment, bundle)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, f)
                .addToBackStack(null)
                .commit()
    }
    fun stepTo(fragment: FragmentView, bundle: Bundle = Bundle()) {
        val f = getFragment(fragment, bundle)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, f)
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
