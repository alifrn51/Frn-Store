package com.frn.frnstore.feature.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.frn.frnstore.data.Banner

class BannerAdapter(fragment: Fragment , val listBanner:List<Banner>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int  = listBanner.size


    override fun createFragment(position: Int): Fragment = BannerFragment.newInstance(listBanner[position])

}