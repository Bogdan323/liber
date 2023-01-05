package com.example.liber.presentation.fragments.maincontent.homefragment.categories.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.liber.data.Product
import com.example.liber.databinding.BestDealItemBinding

class BestDealsAdapter : ListAdapter<Product, BestDealsAdapter.BestDealsViewHolder>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem::class == newItem::class
            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
        }
    }

    inner class BestDealsViewHolder(private val binding: BestDealItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imageBestDeal)
                product.offerPercentage?.let {
                    val remainingPricePercentage = 1f - it
                    val priceAfterOffer = remainingPricePercentage * product.price
                    tvNewBestDealPrice.text = "$ ${String.format("%.2f",priceAfterOffer)}"
                    tvBestDealPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                tvBestDealPrice.text = "$ ${product.price}"
                tvBestDealName.text = product.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestDealsViewHolder {
        return BestDealsViewHolder(
            BestDealItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestDealsViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onClick?.invoke(product)
        }
    }

    var onClick:((Product) -> Unit)? = null
}