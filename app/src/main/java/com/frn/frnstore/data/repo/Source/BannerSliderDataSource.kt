package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.Banner
import io.reactivex.Single

interface BannerSliderDataSource {
    fun getBannerSlider():Single<List<Banner>>
}