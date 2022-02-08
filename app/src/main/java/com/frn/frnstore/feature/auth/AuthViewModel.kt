package com.frn.frnstore.feature.auth

import com.frn.frnstore.common.FrnViewModel
import com.frn.frnstore.data.repo.UserRepository
import io.reactivex.Completable

class AuthViewModel(private val userRepository: UserRepository) : FrnViewModel() {

    fun login(username: String, password: String): Completable {

       // _progressBarLiveData.value = true
        return userRepository.login(username, password)
            .doFinally {
             //   _progressBarLiveData.postValue(false)
            }

    }

    fun signUp(username: String, password: String): Completable {
       // _progressBarLiveData.value = true
        return userRepository.signUp(username, password)
            .doFinally {
              //  _progressBarLiveData.postValue(false)
            }
    }

}