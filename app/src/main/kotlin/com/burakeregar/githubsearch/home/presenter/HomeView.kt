package com.burakeregar.githubsearch.home.presenter

import com.base.mvp.BaseView
import com.burakeregar.githubsearch.home.model.RepoItem

interface HomeView : BaseView {
    fun onSearchResponse(list: List<RepoItem>?)
    fun showProgress()
    fun hideProgress()
    fun noResult()
}
