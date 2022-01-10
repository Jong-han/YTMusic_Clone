package com.jh.ytmusic.ui.artist

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jh.ytmusic.BR
import com.jh.ytmusic.R
import com.jh.ytmusic.base.BaseActivity
import com.jh.ytmusic.databinding.ActivityArtistBinding
import com.jh.ytmusic.setFullScreenLightStatusBar
import com.jh.ytmusic.statusBarHeight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArtistActivity: BaseActivity<ActivityArtistBinding, ArtistViewModel>() {

    override val viewModel: ArtistViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_artist

    private val popularSongAdapter by lazy { PopularSongAdapter().also { it.submitList(viewModel.popularSongList) } }
    private val albumAdapter by lazy { AlbumAdapter().also { it.submitList(viewModel.albumList) } }

    @SuppressLint("ClickableViewAccessibility")
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
        dataBinding.parentPlayer.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
//                dataBinding.parentMain.progress = if (progress * 1.2f >= 1f) 1f else progress * 1.2f
                dataBinding.parentMain.progress = progress
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
            }

        })
//        dataBinding.parentPlayer.setOnTouchListener { v, event ->
//            android.util.Log.i("asdf","TouchEvent :: ${event.action}")
//            when (event.action) {
//                MotionEvent.ACTION_MOVE -> {
//                    dataBinding.parentMain.progress = dataBinding.parentPlayer.progress
//                }
//            }
//            false
//        }
//        dataBinding.parentPlayer.setOnDragListener { v, event ->
////            when (event.action) {
////                DragEvent.ACTION_DRAG_STARTED
////            }
//            android.util.Log.i("asdf","DragEvent :: ${event.action}")
//            true
//        }
//        lifecycleScope.launch {
//            delay(1000)
//            dataBinding.parentPlayer.progress = 1f
//        }

    }
}