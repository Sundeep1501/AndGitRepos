package com.ab.andgitrepos.datasource.retrofit

import com.ab.andgitrepos.datasource.retrofit.model.Contributor
import com.ab.andgitrepos.datasource.retrofit.model.RepoTopicsResponse
import com.ab.andgitrepos.datasource.retrofit.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by sunde_000 on 28/02/2018.
 */
interface GitApiClient {
    /**
     * This method returns repositories based on search criteria.
     * The search options are provided by
     * https://developer.github.com/v3/search/#search-repositories
     */
    @GET("search/repositories")
    fun searchRepositories(@QueryMap(encoded = true) searchQueryMap: Map<String, String>): Observable<SearchResponse>

    /**
     * This method will fetch the topics for the given repo name.
     *
     * CAUTION: API is under development as per docs provided.
     * https://developer.github.com/v3/repos/#list-all-topics-for-a-repository
     */
    @GET("repos/{repoFullName}/topics")
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    fun getRepoTopics(@Path("repoFullName", encoded = true) repoFullName: String): Observable<RepoTopicsResponse>

    /**
     *
     */
    @GET("repos/{repoFullName}/contributors")
    fun getContributors(@Path("repoFullName", encoded = true) repoFullName: String): Observable<List<Contributor>>
}