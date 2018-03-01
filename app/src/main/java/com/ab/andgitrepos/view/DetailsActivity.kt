package com.ab.andgitrepos.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ab.andgitrepos.R
import com.ab.andgitrepos.datasource.retrofit.model.Contributor
import com.ab.andgitrepos.datasource.retrofit.model.Repo
import com.ab.andgitrepos.veiwmodel.DetailsViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity() {

    companion object {
        val BUNDLE_KEY = "repo"

        /**
         * provides the intent to start this activity.
         * @param repo Parcelable repository model object
         */
        fun getIntent(context: Context, repo: Repo): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(BUNDLE_KEY, repo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // init view model for this view
        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        val repo = intent.getParcelableExtra<Repo>(BUNDLE_KEY)

        // check repo data is available
        if (repo == null) {
            Toast.makeText(this, getString(R.string.repo_na), Toast.LENGTH_SHORT).show()
            return
        }

        val detailsViewModel = mViewModel as DetailsViewModel
        detailsViewModel.mTopics.observe(this, Observer<List<String>> { topics -> updateTopics(topics) })
        detailsViewModel.mContributors.observe(this, Observer<List<Contributor>> { topics -> updateContributors(topics) })

        // show repo data
        showRepoData(repo)

        // fetch topics
        detailsViewModel.getTopics(repo.full_name)

        // fetch contributors
        detailsViewModel.getContributors(repo.full_name)
    }

    private fun updateContributors(contributors: List<Contributor>?) {
        //TODO repo will have at least a single contributor. Do we need this condition check?
        if (contributors == null || contributors.isEmpty()) {
            return
        }

        // TODO we are showing only 5 contributors as now. We might need horizontal recyclerview to show all contributors
        for (i in 0..4) {
            if (i > contributors.size - 1) {
                break
            }

            val contributor = contributors[i]

            // inflate imageview to show contributor avatar
            val imageView: ImageView = layoutInflater.inflate(R.layout.view_contributor, contributors_layout, false) as ImageView
            imageView.tag = contributor.login
            imageView.setOnClickListener(contributorClickListener)

            // add inflated view to the layout
            contributors_layout.addView(imageView)

            // load avatar into the imageview
            Glide.with(this).load(contributor.avatar_url).into(imageView)
        }
    }

    private fun updateTopics(topics: List<String>?) {
        if (topics == null || topics.isEmpty()) {
            return
        }

        //TODO if we need to more/all topics, Better we implement our own flow layout(View group) to add items dynamically.
        for (i in 0..4)
            if (topics.size > i) {
                val s = topics[i]
                var view: TextView? = null
                when (i) {
                    0 -> view = topic1
                    1 -> view = topic2
                    2 -> view = topic3
                    3 -> view = topic4
                    4 -> view = topic5
                }
                view!!.text = s
                view.visibility = View.VISIBLE
            } else {
                break
            }
    }

    private fun showRepoData(repo: Repo) {
        fullname.text = repo.full_name

        description.text = repo.description
        // some repos does not have home page urls. 
        if (!TextUtils.isEmpty(repo.homepage)) {
            description.text = description.text.toString() + " " + repo.homepage
        }
        watch.text = repo.watchers_count.toString()
        stars.text = repo.stargazers_count.toString()
        forks.text = repo.forks_count.toString()
        language.text = repo.language
        open_issues.text = repo.open_issues_count.toString()
        clone_url.text = repo.clone_url
        val name = repo.license?.name
        if (name.isNullOrEmpty()) {
            license.text = getString(R.string.na)
        } else {
            license.text = name
        }

    }

    /**
     * handles the contributor image click listener
     */
    private val contributorClickListener: View.OnClickListener = View.OnClickListener { p0 ->
        // show tag which has login name of contributor
        Toast.makeText(this@DetailsActivity, p0!!.tag.toString(), Toast.LENGTH_SHORT).show()
    }
}