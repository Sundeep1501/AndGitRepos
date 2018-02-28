package com.ab.andgitrepos.datasource.retrofit.model

/**
 * Created by sunde_000 on 28/02/2018.
 */
data class Repo(val id: Long, val name: String, val full_name: String, val description: String,
                val owner: Owner, val license: License)

data class Owner(val id: Long)

data class License(val key: String, val name: String, val url: String)