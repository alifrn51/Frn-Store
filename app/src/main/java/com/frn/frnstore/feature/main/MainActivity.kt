    package com.frn.frnstore.feature.main

    import android.os.Bundle
    import com.frn.frnstore.FrnActivity
    import com.frn.frnstore.R
    import timber.log.Timber
    import org.koin.androidx.viewmodel.ext.android.viewModel


    class MainActivity : FrnActivity() {

        val viewModel:MainViewModel by viewModel()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            viewModel.liveData.observe(this ){
                Timber.i(it.toString())
            }

        }
    }