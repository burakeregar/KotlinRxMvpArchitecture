package com.burakeregar.githubsearch.home.di

import com.base.di.ActivityScope
import com.base.util.AppSchedulerProvider
import com.burakeregar.githubsearch.api.Endpoints
import com.burakeregar.githubsearch.home.presenter.HomePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class HomeActivityModule {

    @Provides
    @ActivityScope
    internal fun providesHomePresenter(api: Endpoints, disposable: CompositeDisposable, scheduler: AppSchedulerProvider): HomePresenter =
            HomePresenter(api, disposable, scheduler)
}
