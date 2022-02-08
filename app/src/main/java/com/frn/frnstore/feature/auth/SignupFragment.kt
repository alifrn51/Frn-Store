package com.frn.frnstore.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.frn.frnstore.R
import com.frn.frnstore.common.FrnCompletableObserver
import com.frn.frnstore.common.asyncNetworkRequest
import com.frn.frnstore.databinding.FragmentSignUpBinding
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.emailEt
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignupFragment:Fragment() {

    private lateinit var fragmentSignUpBinding: FragmentSignUpBinding
    var compositeDisposable = CompositeDisposable()

    val viewModel:AuthViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSignUpBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSignUpBinding = FragmentSignUpBinding.bind(view)



        signUpBtn.setOnClickListener{

            viewModel.signUp(emailEt.text.toString() , fragmentSignUpBinding.passwordEt.text.toString())
                .asyncNetworkRequest()
                .subscribe(object :FrnCompletableObserver(compositeDisposable){
                    override fun onComplete() {
                        requireActivity().finish()
                    }

                })

        }

        loginLinkBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, LoginFragment())
            }.commit()
        }



    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }


}