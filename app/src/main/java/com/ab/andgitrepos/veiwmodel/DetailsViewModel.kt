package com.ab.andgitrepos.veiwmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.ab.andgitrepos.MyApplication
import com.ab.andgitrepos.datasource.retrofit.GitApiClient
import com.ab.andgitrepos.datasource.retrofit.model.Contributor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by sunde_000 on 01/03/2018.
 */
class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val TAG = DetailsViewModel::class.java.simpleName
    }

    @Inject lateinit var mGitApiClient: GitApiClient

    // holds topics of the repo
    val mTopics: MutableLiveData<List<String>> = MutableLiveData()
    // holds contributors' details of the repo
    val mContributors: MutableLiveData<List<Contributor>> = MutableLiveData()

    init {
        (application as MyApplication).appComponent.inject(this)
    }

    fun getTopics(repoFullName: String) {
        mGitApiClient.getRepoTopics(repoFullName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ topics -> mTopics.value = topics.names }, { error -> handleError(error) })
    }

    private fun handleError(error: Throwable) {
        Log.e(TAG, error.message)
    }

    fun getContributors(repoFullName: String) {
        mGitApiClient.getContributors(repoFullName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list -> mContributors.value = list }, { error -> handleError(error) })
    }
}