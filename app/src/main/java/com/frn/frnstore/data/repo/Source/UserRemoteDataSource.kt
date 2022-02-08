package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.MessageResponse
import com.frn.frnstore.data.TokenResponse
import com.frn.frnstore.services.http.ApiService
import com.google.gson.JsonObject
import io.reactivex.Single

const val CLIENT_ID = 2
const val GRANT_TYPE = "password"
const val CLIENT_SECRET = "kyj1c9sVcksqGU4scMX7nLDalkjp2WoqQEf8PKAC"

class UserRemoteDataSource(val apiService: ApiService) : UserDataSource {
    override fun login(username: String, password: String): Single<TokenResponse> =
        apiService.login(JsonObject().apply {
            addProperty("username", username)
            addProperty("password", password)
            addProperty("grant_type", GRANT_TYPE)
            addProperty("client_id", CLIENT_ID)
            addProperty("client_secret", CLIENT_SECRET)
        })

    override fun signUp(username: String, password: String): Single<MessageResponse> =
        apiService.signUp(JsonObject().apply {
            addProperty("email" , username)
            addProperty("password", password)
        })

    override fun loadToken() {
        TODO("Not yet implemented")
    }

    override fun saveToken(token: String, refreshToken: String) {
        TODO("Not yet implemented")
    }

}