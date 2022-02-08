package com.frn.frnstore.data.repo

import android.service.autofill.UserData
import com.frn.frnstore.data.TokenContainer
import com.frn.frnstore.data.TokenResponse
import com.frn.frnstore.data.repo.Source.UserDataSource
import io.reactivex.Completable

class UserRepositoryIml(
    val userRemoteData: UserDataSource ,
    val userLocalData: UserDataSource
): UserRepository {
    override fun login(username: String, password: String): Completable  =
        userRemoteData.login(username, password)
            .doOnSuccess {
                doOnSuccessfulLogin(it)
            }.ignoreElement()

    override fun signUp(username: String, password: String): Completable =
        userRemoteData.signUp(username, password)
            .flatMap {
                userRemoteData.login(username, password)
            }
            .doOnSuccess {
                doOnSuccessfulLogin(it)
            }.ignoreElement()

    override fun loadToken() {
       userLocalData.loadToken()
    }

    fun doOnSuccessfulLogin(tokenResponse: TokenResponse){
        TokenContainer.update(tokenResponse.access_token , tokenResponse.refresh_token)
    }
}