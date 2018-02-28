package com.ab.andgitrepos.datasource.retrofit

import com.ab.andgitrepos.datasource.retrofit.model.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by sunde_000 on 28/02/2018.
 */
interface SearchClient {
    /**
     * This method returns repositories based on search criteria.
     * The search options are provided by
     * https://developer.github.com/v3/search/#search-repositories
     *
     */
    @GET("search/repositories")
    fun searchRepositories(@QueryMap searchQueryMap: Map<String, String>): Observable<Repo>
}