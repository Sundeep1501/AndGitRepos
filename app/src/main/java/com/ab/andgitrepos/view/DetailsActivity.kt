package com.ab.andgitrepos.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.ab.andgitrepos.R
import com.ab.andgitrepos.datasource.retrofit.model.Repo
import com.ab.andgitrepos.veiwmodel.DetailsViewModel
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

        (mViewModel as DetailsViewModel).mTopics.observe(this, Observer<List<String>> { topics -> updateTopics(topics) })

        // show repo data
        showRepoData(repo)

        // fetch topics
        (mViewModel as DetailsViewModel).getTopics(repo.full_name)
    }

    private fun updateTopics(topics: List<String>?) {
        if (topics == null || topics.isEmpty()) {
            return
        }
        for (i in 0..4)
            if (topics.size > i) {
                val s = topics[i]
                when (i) {
                    0 -> {
                        topic1.text = s
                    }
                    1 -> {
                        topic2.text = s
                    }
                    2 -> {
                        topic3.text = s
                    }
                    3 -> {
                        topic4.text = s
                    }
                    4 -> {
                        topic5.text = s
                    }
                }
            } else {
                break
            }
        Toast.makeText(this, topics.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun showRepoData(repo: Repo) {
        fullname.text = repo.full_name
        description.text = repo.description
        watch.text = repo.watchers_count.toString()
        stars.text = repo.stargazers_count.toString()
        forks.text = repo.forks_count.toString()
        language.text = repo.language
        open_issues.text = repo.open_issues_count.toString()
        clone_url.text = repo.clone_url
    }
}
