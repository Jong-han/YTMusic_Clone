package com.jh.ytmusic.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding, V: BaseViewModel>: AppCompatActivity() {

    lateinit var dataBinding: T

    abstract val viewModel: V?

    abstract val bindingVariable: Int?

    private var toast: Toast? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initViewsAndEvents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if( getLayoutId() != -1 ) {
            DataBindingUtil.setContentView<T>(this, getLayoutId()).apply {

                dataBinding = this
                lifecycleOwner = this@BaseActivity
                bindingVariable?.let { setVariable( it, viewModel ) }
                executePendingBindings()

            }
        }
        viewModel?.let { lifecycle.addObserver( it ) }

        initViewsAndEvents()

    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel?.let { lifecycle.removeObserver( it ) }

    }

    override fun onBackPressed() {
        setResult( Activity.RESULT_CANCELED, Intent() )
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when( item.itemId ) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showToast( @StringRes resId: Int ) {

        if ( toast != null ) toast?.cancel()
        toast = Toast.makeText( this, resId, Toast.LENGTH_SHORT )
        toast?.show()

    }

    fun cancelToast() {

        toast?.cancel()

    }

}