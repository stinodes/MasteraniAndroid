package com.anko.stinodes.ankoplication.domain

import java.net.URLEncoder

class Credentials(val email: String, val password: String, val remember: Boolean = true) {
    val CHARSET = "UTF-8"
    override fun toString(): String
            = "email=${URLEncoder.encode(email, CHARSET)}" +
                "&password=${URLEncoder.encode(password, CHARSET)}" +
                "&remember=${URLEncoder.encode(remember.toString(), CHARSET)}"
}