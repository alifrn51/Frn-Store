package com.frn.frnstore.data

import android.media.session.MediaSession
import timber.log.Timber

object TokenContainer {

    var token: String? = null
        private set
    var refreshToken: String? = null
        private set

    fun update(token: String?, refreshToken: String?) {
        Timber.i("Access token -> ${token?.substring(0,10)} and RefreshToken -> ${refreshToken?.substring(0, 10)}")
        this.token = token
        this.refreshToken = refreshToken
    }

}