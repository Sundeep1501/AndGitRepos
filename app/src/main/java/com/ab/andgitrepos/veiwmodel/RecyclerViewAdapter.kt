package com.ab.andgitrepos.veiwmodel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ab.andgitrepos.R
import com.ab.andgitrepos.datasource.retrofit.model.Repo

/**
 * Adapter class builds the repo view to show repositories
 */
class RecyclerViewAdapter(private val mRepos: MutableList<Repo>, private val onClickListener: View.OnClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_row, parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repo = mRepos[position]
        val rvHolder = holder as RecyclerViewHolder
        rvHolder.fullname.text = repo.full_name
        rvHolder.description.text = repo.description
        rvHolder.starCount.text = repo.stargazers_count.toString()
        rvHolder.forkCount.text = repo.forks_count.toString()
        rvHolder.language.text = repo.language
    }

    override fun getItemCount(): Int {
        return mRepos.size
    }

    fun getItem(position: Int): Repo? {
        if (position < 0 || position > mRepos.size - 1) {
            return null
        }
        return mRepos[position]
    }

    fun addItems(newList: List<Repo>) {
        mRepos.addAll(newList)
    }

    class RecyclerViewHolder(view: View, onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(view) {
        val fullname: TextView = view.findViewById(R.id.fullname)
        val description: TextView = view.findViewById(R.id.description)
        val starCount: TextView = view.findViewById(R.id.starCount)
        val forkCount: TextView = view.findViewById(R.id.forkCount)
        val language: TextView = view.findViewById(R.id.language)
        private val root: View = view.findViewById(R.id.root)

        init {
            root.setOnClickListener(onClickListener)
        }
    }

}