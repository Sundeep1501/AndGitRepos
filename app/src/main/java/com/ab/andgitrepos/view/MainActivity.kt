package com.ab.andgitrepos.view

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ab.andgitrepos.R
import com.ab.andgitrepos.datasource.retrofit.model.Repo
import com.ab.andgitrepos.veiwmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val lifecycleRegistry = LifecycleRegistry(this)
    lateinit var mViewModel: MainViewModel

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mViewModel.mRepos.observe(this, Observer<List<Repo>> { post -> updatePostDetails(post) })

        mViewModel.getAndroidRepos()
    }

    private fun updatePostDetails(repos: List<Repo>?) {
        if (repos == null) {
            return
        }
        Toast.makeText(this, "" + repos.size, Toast.LENGTH_LONG).show()
    }
}