package com.ab.andgitrepos.view

import android.app.ProgressDialog
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.ab.andgitrepos.R
import com.ab.andgitrepos.datasource.retrofit.model.Repo
import com.ab.andgitrepos.veiwmodel.MainViewModel
import com.ab.andgitrepos.veiwmodel.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val lifecycleRegistry = LifecycleRegistry(this)

    // view model for this view
    lateinit var mViewModel: MainViewModel

    // recycler view adapter
    lateinit var mRecyclerViewAdapter: RecyclerViewAdapter

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init recyclerview adapter
        mRecyclerViewAdapter = RecyclerViewAdapter(ArrayList(), mListItemClickListener)

        // setup recycler view
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = mRecyclerViewAdapter
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // init view model for this view
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mViewModel.mRepos.observe(this, Observer<List<Repo>> { post -> updatePostDetails(post) })

        // fetch repos
        mViewModel.getAndroidRepos()
        progress_bar.visibility = View.VISIBLE
    }

    /**
     * handles the item clicks. Can also be used for child clicks of the view, if required
     */
    private val mListItemClickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view?.id) {
            R.id.root -> {
                val position = recycler_view.getChildAdapterPosition(view)
                Toast.makeText(applicationContext, " pos:" + position, Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * shows repos on the ui
     */
    private fun updatePostDetails(repos: List<Repo>?) {
        progress_bar.visibility = View.GONE
        if (repos == null) {
            return
        }
        mRecyclerViewAdapter.addItems(repos)
        mRecyclerViewAdapter.notifyDataSetChanged()
    }
}