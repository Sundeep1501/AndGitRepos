package com.ab.andgitrepos.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.ab.andgitrepos.R
import com.ab.andgitrepos.datasource.retrofit.model.Repo
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

        val repo = intent.getParcelableExtra<Repo>(BUNDLE_KEY)

        // check repo data is available
        if (repo == null) {
            Toast.makeText(this, getString(R.string.repo_na), Toast.LENGTH_SHORT).show()
            return
        }

        // show repo data
        showRepoData(repo)
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
