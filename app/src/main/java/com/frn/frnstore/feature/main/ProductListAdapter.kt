package com.frn.frnstore.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frn.frnstore.common.formatPrice
import com.frn.frnstore.common.implementSpringAnimationTrait
import com.frn.frnstore.data.Product
import com.frn.frnstore.databinding.ItemProductBinding
import com.frn.frnstore.services.imageLoading.ImageLoadingService

class ProductListAdapter(val imageLoadingService: ImageLoadingService) :
    RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

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
            binding.root.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bindView(listProduct[position])

    override fun getItemCount(): Int = listProduct.size
}