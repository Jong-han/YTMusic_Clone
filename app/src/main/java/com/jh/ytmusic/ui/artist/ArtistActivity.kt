package com.jh.ytmusic.ui.artist

import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
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

    override fun initViewsAndEvents() {

        setFullScreenLightStatusBar()
        (dataBinding.tb.layoutParams as ConstraintLayout.LayoutParams).topMargin = statusBarHeight()

        Glide.with(dataBinding.ivArtist)
            .load("https://kozofficial.com/data/artist/1_main")
            .into(dataBinding.ivArtist)

        dataBinding.sv.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            val progress = scrollY.toFloat() / 830f
            dataBinding.parent.progress = progress
            dataBinding.tvTbTitle.visibility = if (progress >= 0.8f) View.VISIBLE else View.INVISIBLE
            android.util.Log.i("asdf", "scrollY :: $scrollY")
        }

    }
}