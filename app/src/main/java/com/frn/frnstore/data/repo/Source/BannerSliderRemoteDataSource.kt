package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.Banner
import com.frn.frnstore.services.http.ApiService
import io.reactivex.Single

class BannerSliderRemoteDataSource(val apiService: ApiService): BannerSliderDataSource {
    override fun getBannerSlider(): Single<List<Banner>> = apiService.getBannerSlider()
}