package com.frn.frnstore.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frn.frnstore.R
import com.frn.frnstore.common.FrnCompletableObserver
import com.frn.frnstore.common.asyncNetworkRequest
import com.frn.frnstore.databinding.FragmentLoginBinding
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment:Fragment() {

    private lateinit var fragmentLoginBinding: FragmentLoginBinding
    var compositeDisposable = CompositeDisposable()

    val viewModel:AuthViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLoginBinding.inflate(layoutInflater).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLoginBinding = FragmentLoginBinding.bind(view)


        loginBtn.setOnClickListener{
            viewModel.login(emailEt.text.toString() , passwordEt.text.toString())
                .asyncNetworkRequest()
                .subscribe(object :FrnCompletableObserver(compositeDisposable){
                    override fun onComplete() {

                        requireActivity().finish()

                    }

                })
        }

        signUpLinkBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, SignupFragment())
            }.commit()
        }



    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}