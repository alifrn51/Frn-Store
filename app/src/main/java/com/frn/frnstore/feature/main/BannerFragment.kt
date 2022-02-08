package com.frn.frnstore.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frn.frnstore.common.FrnFragment
import com.frn.frnstore.common.EXTRA_KEY_DATA
import com.frn.frnstore.data.Banner
import com.frn.frnstore.databinding.ItemSliderBinding
import com.frn.frnstore.services.imageLoading.ImageLoadingService
import org.koin.android.ext.android.inject
import java.lang.IllegalStateException

class BannerFragment: FrnFragment() {


    private lateinit var  binding: ItemSliderBinding

    val imageLoadingService:ImageLoadingService by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ItemSliderBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemSliderBinding.bind(view)

        val banner = requireArguments().getParcelable<Banner>(EXTRA_KEY_DATA)?:throw IllegalStateException("banner con not null")

        imageLoadingService.loadImage(binding.slider , banner.image)
    }

    companion object {

        fun newInstance(banner : Banner):BannerFragment{

            return BannerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_KEY_DATA , banner)
                }
            }

        }
    }

}