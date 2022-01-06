package com.jh.ytmusic.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jh.ytmusic.R
import com.jh.ytmusic.databinding.ItemSongBinding
import com.jh.ytmusic.model.SongModel

class PopularSongAdapter: ListAdapter<SongModel, PopularSongAdapter.PopularSongViewHolder>(PopularSongDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularSongViewHolder {
        val layoutInflater = LayoutInflater.from( parent.context )
        val binding = DataBindingUtil.inflate<ItemSongBinding>(layoutInflater, R.layout.item_song, parent, false)
        return PopularSongViewHolder( binding )
    }

    override fun onBindViewHolder(holder: PopularSongViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class PopularSongViewHolder(private val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SongModel) {
            binding.run {
                data = item
            }
        }
    }
    class PopularSongDiffUtil: DiffUtil.ItemCallback<SongModel>() {
        override fun areItemsTheSame(oldItem: SongModel, newItem: SongModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: SongModel, newItem: SongModel): Boolean {
            return oldItem == newItem
        }

    }
}