package com.frn.frnstore.feature.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frn.frnstore.FrnFragment
import com.frn.frnstore.common.EXTRA_KEY_DATA
import com.frn.frnstore.common.convertDpToPx
import com.frn.frnstore.data.Product
import com.frn.frnstore.databinding.FragmentMainBinding
import com.frn.frnstore.feature.product.ProductDetailsActivity
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : FrnFragment() , ProductListAdapter.ProductOnClickListener{

    private val viewModel: MainViewModel by viewModel()
    private val productListAdapter:ProductListAdapter by inject()

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
        list_lasted_product.adapter = productListAdapter
        productListAdapter.productOnClickListener = this

        viewModel.productLiveData.observe(viewLifecycleOwner) {
            productListAdapter.listProduct = it as ArrayList<Product>
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

    override fun onClickProduct(product: Product) {
        startActivity(Intent(requireContext() , ProductDetailsActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , product)
        })
    }

}