package com.frn.frnstore.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frn.frnstore.FrnFragment
import com.frn.frnstore.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: FrnFragment() {

    val viewModel:MainViewModel by viewModel()

    lateinit var fragmentMainBinding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMainBinding.inflate(layoutInflater).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentMainBinding = FragmentMainBinding.bind(view)

    }

}