package com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.ui.AnimeFragmentUI

class AnimeFragment(val args: Bundle): Fragment() {

    val ui = AnimeFragmentUI()

    companion object {
        fun create(bundle: Bundle = Bundle()): AnimeFragment {
            val fragment = AnimeFragment(bundle)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}