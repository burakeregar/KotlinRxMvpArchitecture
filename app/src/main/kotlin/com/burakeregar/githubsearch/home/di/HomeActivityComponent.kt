package com.burakeregar.githubsearch.home.di

import com.base.di.ActivityScope
import com.base.di.component.AppComponent
import com.burakeregar.githubsearch.home.HomeActivity

import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(HomeActivityModule::class))
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)
}
