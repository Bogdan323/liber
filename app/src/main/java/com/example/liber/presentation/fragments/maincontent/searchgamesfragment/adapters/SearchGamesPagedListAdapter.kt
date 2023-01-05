package com.example.liber.presentation.fragments.maincontent.searchgamesfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.liber.R
import com.example.liber.databinding.GameItemCardBinding
import com.example.liber.domain.model.Game

class SearchGamesPagedListAdapter() :
    PagingDataAdapter<Game, SearchGamesPagedListAdapter.GameCardViewHolder>(diff) {

    var onItemClick: ((Game) -> Unit)? = null

    companion object {
        val diff = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem::class == newItem::class
            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem.id == newItem.id
        }
    }

    inner class GameCardViewHolder(val binding: GameItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.apply {
                if(game.backgroundImage != null)
                    Glide.with(itemView).load(game.backgroundImage).into(ivGameImage)
                tvGameTitle.text = game.name
                tvGameRating.text = game.rating.toString()
                tvGameRelease.text = root.context.getString(R.string.game_release_date, game.released)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameCardViewHolder {
        return GameCardViewHolder(
            GameItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameCardViewHolder, position: Int) {
        val theGame = getItem(position)
        holder.bind(theGame!!)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(theGame)
        }
    }
}