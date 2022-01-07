package com.testexample.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    val binding: B by lazy {
        DataBindingUtil.setContentView(this, getLayout()) as B
    }

    abstract fun getLayout(): Int
    abstract fun initView()
    abstract fun initObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObservers()
    }

}