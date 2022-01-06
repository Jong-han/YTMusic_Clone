package com.jh.ytmusic.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jh.ytmusic.R
import com.jh.ytmusic.databinding.ItemAlbumBinding
import com.jh.ytmusic.model.AlbumModel

class AlbumAdapter: ListAdapter<AlbumModel, AlbumAdapter.AlbumViewHolder>(AlbumDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from( parent.context )
        val binding = DataBindingUtil.inflate<ItemAlbumBinding>(layoutInflater, R.layout.item_album, parent, false)
        return AlbumViewHolder( binding )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlbumModel) {
            binding.run {
                data = item
            }
        }
    }

    class AlbumDiffUtil: DiffUtil.ItemCallback<AlbumModel>() {
        override fun areItemsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem == newItem
        }

    }
}