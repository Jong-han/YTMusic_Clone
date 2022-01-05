package com.jh.ytmusic

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.core.view.WindowCompat

/** Control Status Bar */
@Suppress("DEPRECATION")
fun Activity.setFullScreenLightStatusBar(color: Int = Color.TRANSPARENT ) {

    window.statusBarColor = color
    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.R ) {
        WindowCompat.setDecorFitsSystemWindows( window, false )
//        WindowCompat.getInsetsController( window, window.decorView )?.apply {
//            isAppearanceLightStatusBars = isLightStatusBar
//        }
    } else {
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }

}

fun Activity.statusBarHeight(): Int {

    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
    else 0

}

///** Control Status Bar */
//@Suppress("DEPRECATION")
//fun Activity.setFullScreenLightStatusBar(isLightStatusBar:Boolean, color: Int = Color.TRANSPARENT ) {
//
//    window.statusBarColor = color
//    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.R ) {
//        WindowCompat.setDecorFitsSystemWindows( window, false )
//        WindowCompat.getInsetsController( window, window.decorView )?.apply {
//            isAppearanceLightStatusBars = isLightStatusBar
//        }
//    } else {
//        if( isLightStatusBar ) {
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        } else {
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
//        }
//    }
//
//}