package com.frn.frnstore

import android.app.Application
import com.frn.frnstore.data.repo.ProductRepositoryIml
import com.frn.frnstore.data.repo.ProductsRepository
import com.frn.frnstore.data.repo.Source.ProductLocalDataSource
import com.frn.frnstore.data.repo.Source.ProductRemoteDataSource
import com.frn.frnstore.feature.main.MainViewModel
import com.frn.frnstore.services.http.ApiService
import com.frn.frnstore.services.http.createApiServiceInstance
import io.reactivex.Single
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant()
        val myModel = module {
            single<ApiService> { createApiServiceInstance() }
            factory<ProductsRepository> {
                ProductRepositoryIml(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }
            viewModel { MainViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModel)
        }
    }

}