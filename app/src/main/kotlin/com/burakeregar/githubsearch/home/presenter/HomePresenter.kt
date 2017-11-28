package com.burakeregar.githubsearch.home.presenter

import com.base.mvp.BasePresenter
import com.base.util.SchedulerProvider
import com.burakeregar.githubsearch.api.Endpoints
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<HomeView>(disposable, scheduler) {

    fun getRepos(searchKey: String) {
        view?.showProgress()
        disposable.add(api.search(searchKey)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            view?.hideProgress()
                            view?.onSearchResponse(result?.items)

                            if (result.items == null || result.items.isEmpty()) {
                                view?.noResult()
                            }
                        },
                        { _ ->
                            view?.hideProgress()
                            view?.onError()
                        })
        )
    }
}