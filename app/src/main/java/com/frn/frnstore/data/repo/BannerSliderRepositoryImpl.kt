package com.frn.frnstore.data.repo

import com.frn.frnstore.data.Banner
import com.frn.frnstore.data.repo.Source.BannerSliderRemoteDataSource
import io.reactivex.Single

class BannerSliderRepositoryImpl(val bannerSliderDataSource: BannerSliderRemoteDataSource) :
    BannerSliderRepository {
    override fun getBannerSlider(): Single<List<Banner>> = bannerSliderDataSource.getBannerSlider()
}