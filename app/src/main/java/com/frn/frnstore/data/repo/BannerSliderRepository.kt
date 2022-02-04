package com.frn.frnstore.data.repo

import com.frn.frnstore.data.Banner
import io.reactivex.Single

interface BannerSliderRepository {

    fun getBannerSlider():Single<List<Banner>>
}