package com.frn.frnstore.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frn.frnstore.FrnFragment
import com.frn.frnstore.common.convertDpToPx
import com.frn.frnstore.data.Product
import com.frn.frnstore.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainFragment : FrnFragment() {

    private val viewModel: MainViewModel by viewModel()
    private val listProduct:ProductListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMainBinding.inflate(layoutInflater).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        list_lasted_product.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        viewModel.productLiveData.observe(viewLifecycleOwner) {
            listProduct.listProduct = it as ArrayList<Product>
            list_lasted_product.adapter = listProduct
        }

        viewModel.progressBarLiveData.observe(viewLifecycleOwner){
            setProgressIndicator(it)
        }

        viewModel.bannerSliderLiveData.observe(viewLifecycleOwner){
            val bannerAdapter = BannerAdapter(this , it)
            sliderViewPager.adapter = bannerAdapter
            val sliderViewpagerHeight = ((sliderViewPager.measuredWidth - convertDpToPx(32 , requireContext())) * 173 ) / 328
            val layoutParams = sliderViewPager.layoutParams
            layoutParams.height = sliderViewpagerHeight
            sliderViewPager.layoutParams = layoutParams

            sliderIndicator.setViewPager2(sliderViewPager)

        }

    }

}