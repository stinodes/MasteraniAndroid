package com.anko.stinodes.ankoplication.mainactivity.loginfragment.ui

import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.widget.EditText
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.domain.Credentials
import com.anko.stinodes.ankoplication.mainactivity.loginfragment.ToolbarLoginFragment
import org.jetbrains.anko.*

class ToolbarLoginUI(val onSubmit: (Credentials) -> Unit): AnkoComponent<ToolbarLoginFragment> {

    lateinit var emailField: EditText
    lateinit var passwordField: EditText

    override fun createView(ui: AnkoContext<ToolbarLoginFragment>): View = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            gravity = Gravity.CENTER

            linearLayout {
                gravity = Gravity.CENTER
                textView {
                    text = resources.getString(R.string.sign_in)
                    textColor = ContextCompat.getColor(context, R.color.white2)
                    textSize = 22f
                    setShadowLayer(3f, 0f, 2f, ContextCompat.getColor(context, R.color.black))

                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        setTextAppearance(R.style.bold_text)
                }.lparams {
                    rightMargin = dip(16)
                }

                textView {
                    text = resources.getString(R.string.to_view_info)
                    textColor = ContextCompat.getColor(context, R.color.white2)
                    textSize = 16f
                    setShadowLayer(3f, 0f, 2f, ContextCompat.getColor(context, R.color.black))
                }
            }

            emailField = editText {
                val drawable = ContextCompat.getDrawable(context, R.drawable.white_filled_edittext)
                backgroundDrawable = drawable
                inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                gravity = Gravity.CENTER
                padding = dip(4)
                textSize = 16f
                textColor = ContextCompat.getColor(context, R.color.black)

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    elevation = 5f

            }.lparams(width = dip(232)) {
                gravity = Gravity.CENTER
                margin = dip(4)
            }

            passwordField = editText {
                val drawable = ContextCompat.getDrawable(context, R.drawable.white_filled_edittext)
                backgroundDrawable = drawable
                inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                gravity = Gravity.CENTER
                padding = dip(4)
                textSize = 16f
                textColor = ContextCompat.getColor(context, R.color.black)
                transformationMethod = PasswordTransformationMethod()

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    elevation = 5f

            }.lparams(width = dip(232)) {
                gravity = Gravity.CENTER
                margin = dip(4)
            }

            imageButton {
                val drawable = ContextCompat.getDrawable(context, R.drawable.primary_filled_circle)
                backgroundDrawable = drawable
                padding = 0

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    elevation = 5f

                onClick {
                    onSubmit(
                            Credentials(
                                    emailField.text.toString(),
                                    passwordField.text.toString(),
                                    true
                            )
                    )
                }
            }.lparams(width = dip(32), height = dip(32)) {
                gravity = Gravity.CENTER
                margin = dip(4)
            }
        }
    }

}