package com.ab.andgitrepos.datasource.retrofit.model

/**
 * Created by sunde_000 on 28/02/2018.
 */
data class Repo(val id: Long, val name: String, val full_name: String, val description: String,
                val language: String, val stargazers_count: Int, val forks_count: Int,
                val owner: Owner, val license: License)

/**
 * repo's owner model
 */
data class Owner(val id: Long)

/**
 * repo's license model
 */
data class License(val key: String, val name: String, val url: String)

/**
 * repo search response model
 */
data class SearchResponse(val items: List<Repo>)