package com.jh.ytmusic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel>: Fragment() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    lateinit var dataBinding: T

    abstract val viewModel: V?

    abstract val bindingVariable: Int

    abstract fun initViewsAndEvents()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate( layoutInflater, getLayoutId(), container, false )
        dataBinding.lifecycleOwner = this
        dataBinding.setVariable( bindingVariable, viewModel )

        viewModel?.let { viewLifecycleOwner.lifecycle.addObserver( it ) }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewsAndEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel?.let { viewLifecycleOwner.lifecycle.removeObserver( it ) }

    }

    fun showToast( @StringRes resId: Int ) {

        ( requireActivity() as BaseActivity<*, *>).showToast( resId )

    }

    fun cancelToast() {

        ( requireActivity() as BaseActivity<*, *>).cancelToast()

    }

}