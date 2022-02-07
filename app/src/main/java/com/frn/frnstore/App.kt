package com.frn.frnstore

import android.app.Application
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import com.frn.frnstore.data.repo.*
import com.frn.frnstore.data.repo.Source.BannerSliderRemoteDataSource
import com.frn.frnstore.data.repo.Source.CommentRemoteDataSource
import com.frn.frnstore.data.repo.Source.ProductLocalDataSource
import com.frn.frnstore.data.repo.Source.ProductRemoteDataSource
import com.frn.frnstore.feature.listProduct.ListProductViewModel
import com.frn.frnstore.feature.main.MainViewModel
import com.frn.frnstore.feature.main.ProductListAdapter
import com.frn.frnstore.feature.product.ProductDetailsViewModel
import com.frn.frnstore.feature.product.comment.CommentsViewModel
import com.frn.frnstore.services.http.ApiService
import com.frn.frnstore.services.http.createApiServiceInstance
import com.frn.frnstore.services.imageLoading.ImageLoadingService
import com.frn.frnstore.services.imageLoading.ImageLoadingServiceImpl
import io.reactivex.Single
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)


        val myModel = module {
            single<ApiService> { createApiServiceInstance() }
            single<ImageLoadingService> { ImageLoadingServiceImpl() }
            factory<ProductsRepository> {
                ProductRepositoryIml(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }
            factory<BannerSliderRepository> {
                BannerSliderRepositoryImpl(BannerSliderRemoteDataSource(get()))
            }
            factory<CommentRepository> { CommentRepositoryIml(CommentRemoteDataSource(get())) }
            factory { (viewType :Int)->ProductListAdapter(viewType ,get()) }
            viewModel { MainViewModel(get(), get()) }
            viewModel { (bundle: Bundle) -> ProductDetailsViewModel(bundle , get()) }
            viewModel { (productId: Int) -> CommentsViewModel(productId , get()) }
            viewModel { (sort :Int)-> ListProductViewModel(sort , get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModel)
        }
    }

}