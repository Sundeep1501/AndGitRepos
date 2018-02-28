package com.ab.andgitrepos.veiwmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.ab.andgitrepos.MyApplication
import com.ab.andgitrepos.datasource.retrofit.SearchClient
import com.ab.andgitrepos.datasource.retrofit.model.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by sunde_000 on 28/02/2018.
 */
open class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var mSearchClient: SearchClient

    val mRepos: MutableLiveData<List<Repo>> = MutableLiveData()

    init {
        (application as MyApplication).appComponent.inject(this)
        mRepos.value = null
    }

    fun getAndroidRepos() {
        // get yesterday's date
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val format = YYYYMMDD_FORMAT.format(cal.time)

        // prepare query map
        val searchQueryMap = HashMap<String, String>()
        searchQueryMap.put("q", "android+language:kotlin+pushed:>" + format)
        searchQueryMap.put("sort", "stars")
        searchQueryMap.put("order", "desc")

        mSearchClient.searchRepositories(searchQueryMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { repos ->
                            mRepos.value = repos.items
                        },
                        { error ->
                            handleAPIFailure(error)
                        }
                )
    }

    private fun handleAPIFailure(error: Throwable) {
        if (error is IOException) {
            Toast.makeText(getApplication(), "No Internet.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(getApplication(), "ERROR:" + error.message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        val YYYYMMDD_FORMAT = SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH)
    }
}