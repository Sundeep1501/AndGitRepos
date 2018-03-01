package com.ab.andgitrepos.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.ab.andgitrepos.R
import com.ab.andgitrepos.datasource.retrofit.model.Repo
import com.ab.andgitrepos.veiwmodel.MainViewModel
import com.ab.andgitrepos.veiwmodel.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    // recycler view adapter
    private lateinit var mRecyclerViewAdapter: RecyclerViewAdapter

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

        // observe for changes in repo data variable
        (mViewModel as MainViewModel).mRepos.observe(this, Observer<List<Repo>> { post -> updatePostDetails(post) })

        // fetch repos
        (mViewModel as MainViewModel).getAndroidRepos()
        progress_bar.visibility = View.VISIBLE
    }

    /**
     * handles the item clicks. Can also be used for child clicks of the view, if required
     */
    private val mListItemClickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view?.id) {
            R.id.root -> {
                val position = recycler_view.getChildAdapterPosition(view)
                val repo = mRecyclerViewAdapter.getItem(position)
                if (repo != null) {
                    // show details of the repo
                    startActivity(DetailsActivity.getIntent(this, repo))
                } else {
                    Toast.makeText(this, getString(R.string.repo_na), Toast.LENGTH_SHORT).show()
                }
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