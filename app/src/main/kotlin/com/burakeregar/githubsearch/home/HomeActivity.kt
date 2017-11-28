package com.burakeregar.githubsearch.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.base.BaseActivity
import com.base.helper.SpHelper
import com.burakeregar.githubsearch.R
import com.burakeregar.githubsearch.home.di.DaggerHomeActivityComponent
import com.burakeregar.githubsearch.home.di.HomeActivityModule
import com.burakeregar.githubsearch.home.presenter.HomePresenter
import com.burakeregar.githubsearch.home.presenter.HomeView
import com.burakeregar.githubsearch.home.viewholder.RepoViewHolder
import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder
import com.burakeregar.easiestgenericrecycleradapter.base.GenericRecyclerAdapter
import com.burakeregar.githubsearch.home.model.RepoItem
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.home_activity.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView, MaterialSearchBar.OnSearchActionListener {

    @Inject
    lateinit var presenter: HomePresenter

    @Inject
    lateinit var spHelper: SpHelper

    private lateinit var rvAdapter: GenericRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        initAdapter()
        initSearchView()
    }

    private fun initAdapter() {
        rvAdapter = GenericAdapterBuilder().addModel(
                R.layout.row_repos,
                RepoViewHolder::class.java,
                RepoItem::class.java)
                .execute()

        with(homeRv) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = rvAdapter
        }
    }

    private fun initSearchView() {
        with(homeSearchView) {
            setHint(getString(R.string.searchview_hint))
            setSpeechMode(true)
            setOnSearchActionListener(this@HomeActivity)
            val suggestions = spHelper.getSuggestions()
            if (suggestions != null) {
                lastSuggestions = suggestions
            }
        }
    }

    override fun onActivityInject() {
        DaggerHomeActivityComponent.builder().appComponent(getAppcomponent())
                .homeActivityModule(HomeActivityModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }

    override fun onSearchResponse(list: List<RepoItem>?) {
        rvAdapter.setList(list)
    }

    override fun showProgress() {
        homeRv.visibility = View.GONE
        homeShimmer.visibility = View.VISIBLE
        homeLogo.visibility = View.GONE
        homeShimmer.startShimmerAnimation()
    }

    override fun hideProgress() {
        homeRv.visibility = View.VISIBLE
        homeShimmer.visibility = View.GONE
        homeShimmer.stopShimmerAnimation()
    }

    override fun onButtonClicked(buttonCode: Int) {}

    override fun onSearchStateChanged(enabled: Boolean) {}

    override fun onSearchConfirmed(text: CharSequence?) {
        presenter.getRepos(text?.toString() ?: "")
    }

    override fun noResult() {
        homeLogo.visibility = View.VISIBLE
        toast("We couldn't find any repository.")
    }

    override fun onDestroy() {
        super.onDestroy()
        spHelper.setSuggestions(homeSearchView.lastSuggestions)
    }

    @Subscribe
    fun onRowClicked(item: RepoItem) {
        toast("Author's name: ${item.owner?.login}")
    }

}
