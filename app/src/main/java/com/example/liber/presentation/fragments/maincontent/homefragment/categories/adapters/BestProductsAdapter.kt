package com.example.liber.presentation.fragments.maincontent.homefragment.categories.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.liber.data.Product
import com.example.liber.databinding.ProductItemBinding

class BestProductsAdapter : ListAdapter<Product, BestProductsAdapter.BestProductViewHolder>(diff) {

    inner class BestProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgProduct)
                product.offerPercentage?.let {
                    val remainingPricePercentage = 1f - it
                    val priceAfterOffer = remainingPricePercentage * product.price
                    tvNewProductPrice.text = "$ ${String.format("%.2f",priceAfterOffer)}"
                    tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                if (product.offerPercentage == null)
                    tvNewProductPrice
                tvPrice.text = "$ ${product.price}"
                tvName.text = product.name
            }
        }
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem::class == newItem::class
            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onClick?.invoke(product)
        }
    }

    var onClick:((Product) -> Unit)? = null
}