package com.anko.stinodes.ankoplication.mainactivity.toolbar.loginfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.Credentials
import com.anko.stinodes.ankoplication.mainactivity.toolbar.loginfragment.ui.ToolbarLoginUI
import com.anko.stinodes.ankoplication.web.createMAService
import org.jetbrains.anko.AnkoContext
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ToolbarLoginFragment: Fragment() {

    val ui = ToolbarLoginUI({ handleLogin(it) })

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        return ui.createView(
                AnkoContext.Companion.create(activity, this)
        )
    }

    fun handleLogin(credentials: Credentials) {
        createMAService()
                .login(credentials = credentials.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d("LOGIN", "WAS SUCCESSFUL?")
                        },
                        {
                            Log.e("LOGIN", "FAILED")
                            Log.e("LOGIN", it.message)
                            it.printStackTrace()
                        }
                )
    }

}