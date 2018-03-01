package com.ab.andgitrepos.view

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity

/**
 * Created by sunde_000 on 01/03/2018.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private val lifecycleRegistry = LifecycleRegistry(this)

    // view model for this view
    lateinit var mViewModel: ViewModel

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }
}