package com.frn.frnstore.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frn.frnstore.R
import com.frn.frnstore.common.formatPrice
import com.frn.frnstore.common.implementSpringAnimationTrait
import com.frn.frnstore.data.Product
import com.frn.frnstore.databinding.ItemProductBinding
import com.frn.frnstore.services.imageLoading.ImageLoadingService


const val VIEW_TYPE_ROUND = 0
const val VIEW_TYPE_SMALL = 1
const val VIEW_TYPE_LAGER = 2

class ProductListAdapter(
    var viewType: Int = VIEW_TYPE_ROUND,
    val imageLoadingService: ImageLoadingService
) :
    RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    var productOnClickListener: ProductOnClickListener? = null
    var listProduct = ArrayList<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemProductBinding = ItemProductBinding.bind(itemView)

        fun bindView(product: Product) {
            imageLoadingService.loadImage(binding.imageProduct, product.image)
            binding.titleProduct.text = product.title
            binding.priceAfter.text = formatPrice(product.price)
            binding.priceBefore.text = formatPrice(product.previous_price)
            binding.root.implementSpringAnimationTrait()
            binding.root.setOnClickListener {
                productOnClickListener?.onClickProduct(product)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutResId  = when(viewType){
            VIEW_TYPE_ROUND -> R.layout.item_product
            VIEW_TYPE_SMALL -> R.layout.item_product_rec
            VIEW_TYPE_LAGER -> R.layout.item_product_fix
            else -> {throw IllegalAccessError("Error Undefine!")}
        }
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(layoutResId , parent , false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bindView(listProduct[position])

    override fun getItemCount(): Int = listProduct.size

    interface ProductOnClickListener {
        fun onClickProduct(product: Product)
    }
}