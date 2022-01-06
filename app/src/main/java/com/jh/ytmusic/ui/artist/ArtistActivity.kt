package com.jh.ytmusic.ui.artist

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jh.ytmusic.BR
import com.jh.ytmusic.R
import com.jh.ytmusic.base.BaseActivity
import com.jh.ytmusic.databinding.ActivityArtistBinding
import com.jh.ytmusic.setFullScreenLightStatusBar
import com.jh.ytmusic.statusBarHeight

class ArtistActivity: BaseActivity<ActivityArtistBinding, ArtistViewModel>() {

    override val viewModel: ArtistViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_artist

    private val popularSongAdapter by lazy { PopularSongAdapter().also { it.submitList(viewModel.popularSongList) } }
    private val albumAdapter by lazy { AlbumAdapter().also { it.submitList(viewModel.albumList) } }

    override fun initViewsAndEvents() {

        setFullScreenLightStatusBar()
        (dataBinding.tb.layoutParams as ConstraintLayout.LayoutParams).topMargin = statusBarHeight()

        Glide.with(dataBinding.ivArtist)
            .load("https://kozofficial.com/data/artist/1_main")
            .into(dataBinding.ivArtist)

        dataBinding.nav.itemIconTintList = null

        dataBinding.rvPopularSong.adapter = popularSongAdapter
        dataBinding.rvAlbum.run {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(this@ArtistActivity).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }
        dataBinding.sv.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            android.util.Log.i("asdf","scrollY:: $scrollY")
            val progress = scrollY.toFloat() / 1120f
            dataBinding.parent.progress = progress
            dataBinding.tvTbTitle.visibility = if (progress >= 0.8f) View.VISIBLE else View.INVISIBLE
            if (progress >= 1f) {
                dataBinding.tb.setBackgroundColor(Color.BLACK)
                window.statusBarColor = Color.BLACK
            } else {
                dataBinding.tb.setBackgroundColor(Color.TRANSPARENT)
                window.statusBarColor = Color.TRANSPARENT
            }
            android.util.Log.i("asdf", "scrollY :: $scrollY")
        }

    }
}