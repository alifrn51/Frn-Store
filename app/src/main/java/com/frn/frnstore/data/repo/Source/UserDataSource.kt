package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.MessageResponse
import com.frn.frnstore.data.TokenResponse
import io.reactivex.Completable
import io.reactivex.Single

interface UserDataSource {

    fun login(username: String, password: String): Single<TokenResponse>
    fun signUp(username: String, password: String): Single<MessageResponse>
    fun loadToken()
    fun saveToken(token: String , refreshToken: String)

}