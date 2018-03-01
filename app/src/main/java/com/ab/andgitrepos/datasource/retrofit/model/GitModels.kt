package com.ab.andgitrepos.datasource.retrofit.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by sunde_000 on 28/02/2018.
 */
data class Repo(val id: Long, val name: String, val full_name: String, val description: String?,
                val language: String, val stargazers_count: Int, val forks_count: Int,
                val watchers_count: Int, val open_issues_count: Int, val clone_url: String,
                val homepage: String?,
                val owner: Owner, val license: License?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Owner::class.java.classLoader),
            parcel.readParcelable(License::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(full_name)
        parcel.writeString(description)
        parcel.writeString(language)
        parcel.writeInt(stargazers_count)
        parcel.writeInt(forks_count)
        parcel.writeInt(watchers_count)
        parcel.writeInt(open_issues_count)
        parcel.writeString(clone_url)
        parcel.writeString(homepage)
        parcel.writeParcelable(owner, flags)
        parcel.writeParcelable(license, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repo> {
        override fun createFromParcel(parcel: Parcel): Repo {
            return Repo(parcel)
        }

        override fun newArray(size: Int): Array<Repo?> {
            return arrayOfNulls(size)
        }
    }
}

/**
 * repo's owner model
 */
data class Owner(val id: Long) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner(parcel)
        }

        override fun newArray(size: Int): Array<Owner?> {
            return arrayOfNulls(size)
        }
    }
}

/**
 * repo's license model
 */
data class License(val key: String, val name: String, val url: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<License> {
        override fun createFromParcel(parcel: Parcel): License {
            return License(parcel)
        }

        override fun newArray(size: Int): Array<License?> {
            return arrayOfNulls(size)
        }
    }
}

/**
 * repo search response model
 */
data class SearchResponse(val items: List<Repo>)

/**
 * Repo's topics response model
 */
data class RepoTopicsResponse(val names: List<String>)