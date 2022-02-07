package com.frn.frnstore.feature.listProduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.frn.frnstore.FrnActivity
import com.frn.frnstore.R
import com.frn.frnstore.common.EXTRA_KEY_DATA
import com.frn.frnstore.data.Product
import com.frn.frnstore.databinding.ActivityListProductBinding
import com.frn.frnstore.feature.main.ProductListAdapter
import com.frn.frnstore.feature.main.VIEW_TYPE_LAGER
import com.frn.frnstore.feature.main.VIEW_TYPE_ROUND
import com.frn.frnstore.feature.main.VIEW_TYPE_SMALL
import com.frn.frnstore.feature.product.ProductDetailsActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_list_product.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ListProductActivity : FrnActivity() , ProductListAdapter.ProductOnClickListener {

    private lateinit var binding: ActivityListProductBinding

    private val viewModel: ListProductViewModel by viewModel {
        parametersOf(
            intent.extras!!.getInt(
                EXTRA_KEY_DATA
            )
        )
    }

    private val productListAdapter: ProductListAdapter by inject { parametersOf(VIEW_TYPE_SMALL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this, 2)
        list_product.layoutManager = gridLayoutManager
        list_product.adapter = productListAdapter
        productListAdapter.productOnClickListener = this

        viewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

        grid.setOnClickListener {
            if (productListAdapter.viewType == VIEW_TYPE_SMALL) {
                grid.setImageResource(R.drawable.ic_large)
                productListAdapter.viewType = VIEW_TYPE_LAGER
                gridLayoutManager.spanCount = 1
                productListAdapter.notifyDataSetChanged()
            } else {
                grid.setImageResource(R.drawable.ic_grid)
                productListAdapter.viewType = VIEW_TYPE_SMALL
                gridLayoutManager.spanCount = 2
                productListAdapter.notifyDataSetChanged()

            }
        }

        viewModel.selectedSortLiveData.observe(this ){
            txt_sort.text = getString(it)
        }

        selected_sort.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(this)
                .setSingleChoiceItems(R.array.sort, viewModel.sort) { dialog, sort ->
                    viewModel.onSelectedSortChangeByUser(sort)
                    dialog.dismiss()
                }.setTitle(getString(R.string.sort))

            dialog.show()
        }

        viewModel.productLiveData.observe(this) {
            productListAdapter.listProduct = it as ArrayList<Product>

        }


    }

    override fun onClickProduct(product: Product) {
        startActivity(Intent(this , ProductDetailsActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , product)
        })
    }


}