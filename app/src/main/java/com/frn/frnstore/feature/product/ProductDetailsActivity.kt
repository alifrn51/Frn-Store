package com.frn.frnstore.feature.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frn.frnstore.common.FrnActivity
import com.frn.frnstore.common.*
import com.frn.frnstore.data.Comment
import com.frn.frnstore.databinding.ActivityProductBinding
import com.frn.frnstore.feature.product.comment.CommentsActivity
import com.frn.frnstore.services.imageLoading.ImageLoadingService
import com.frn.frnstore.views.scroll.ObservableScrollViewCallbacks
import com.frn.frnstore.views.scroll.ScrollState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_product.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailsActivity : FrnActivity() {

    val compositeDisposable = CompositeDisposable()

    lateinit var activityProductBinding: ActivityProductBinding
    private val productDetailsViewModel: ProductDetailsViewModel by viewModel { parametersOf(intent.extras) }
    private val imageLoadingService: ImageLoadingService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityProductBinding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(activityProductBinding.root)

        productDetailsViewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

        productDetailsViewModel.productLiveData.observe(this) { product ->
            imageLoadingService.loadImage(imageProduct, product.image)
            currentPrice.text = formatPrice(product.price)
            previousPrice.text = formatPrice(product.previous_price)
            titleProduct.text = product.title
            titleProductToolbar.text = product.title
        }


        imageProduct.post {
            val productHeight = imageProduct.height
            val toolbar = toolbar
            val imageProduct = imageProduct
            observableScrollview.addScrollViewCallbacks(object : ObservableScrollViewCallbacks {
                override fun onScrollChanged(
                    scrollY: Int,
                    firstScroll: Boolean,
                    dragging: Boolean
                ) {
                    toolbar.alpha = scrollY.toFloat() / productHeight.toFloat()
                    imageProduct.translationY = scrollY.toFloat() / 2
                }

                override fun onDownMotionEvent() {

                }

                override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

                }

            })
        }


        val commentAdapter = CommentAdapter()
        listComment.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)
        listComment.isNestedScrollingEnabled = false

        productDetailsViewModel.commentsLiveData.observe(this) { comments ->

            commentAdapter.comments = comments as ArrayList<Comment>
            listComment.adapter = commentAdapter

            if(comments.size > 3) {
                viewAllComments.visibility = View.VISIBLE

                viewAllComments.setOnClickListener{
                    startActivity(Intent(this , CommentsActivity::class.java).apply {
                        putExtra(EXTRA_KEY_ID , productDetailsViewModel.productLiveData.value!! .id)
                    })
                }
            }

        }

        addToCart.setOnClickListener {
            productDetailsViewModel.onAddProductToCart()
                .asyncNetworkRequest()
                .subscribe(object : FrnCompletableObserver( compositeDisposable){
                    override fun onComplete() {
                        TODO("Not yet implemented")
                    }
                })
        }



    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}