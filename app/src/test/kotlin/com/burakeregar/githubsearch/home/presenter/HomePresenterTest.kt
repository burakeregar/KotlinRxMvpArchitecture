package com.burakeregar.githubsearch.home.presenter

import com.burakeregar.githubsearch.api.Endpoints
import com.burakeregar.githubsearch.home.model.RepoItem
import com.burakeregar.githubsearch.home.model.RepoResponse
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.util.TestSchedulerProvider
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import io.reactivex.schedulers.TestScheduler
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times


/**
 * Created by Burak Eregar on 18.11.2017.
 * burakeregar@gmail.com
 * https://github.com/burakeregar
 */

class HomePresenterTest {

    private val view: HomeView = mock()
    private val api: Endpoints = mock()
    private lateinit var presenter: HomePresenter
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        presenter = HomePresenter(api, compositeDisposable, testSchedulerProvider)
        presenter.attachView(view)
    }

    @Test
    fun test_getRepos_should_callSuccess() {
        val mockedResponse: RepoResponse = mock()
        val searchKey = "test"

        doReturn(Observable.just(mockedResponse))
                .`when`(api)
                .search(searchKey)

        presenter.getRepos(searchKey)

        testScheduler.triggerActions()

        verify(view).showProgress()
        verify(view).onSearchResponse(mockedResponse.items)
        verify(view).hideProgress()

    }

    @Test
    fun test_getRepos_shouldNot_callNoResult() {
        val mockedResponse: RepoResponse = mock()
        val items = ArrayList<RepoItem>()
        val searchKey = "test"

        items.add(Mockito.mock(RepoItem::class.java))

        `when`(mockedResponse.items).thenReturn(items)

        doReturn(Observable.just(mockedResponse))
                .`when`(api)
                .search(searchKey)

        presenter.getRepos(searchKey)

        testScheduler.triggerActions()

        verify(view, times(0)).noResult()
    }

    @Test
    fun test_getRepos_should_callNoResult() {
        val mockedResponse: RepoResponse = mock()
        val items = ArrayList<RepoItem>()
        val searchKey = "test"

        `when`(mockedResponse.items).thenReturn(items)

        doReturn(Observable.just(mockedResponse))
                .`when`(api)
                .search(searchKey)

        presenter.getRepos(searchKey)

        testScheduler.triggerActions()

        verify(view).noResult()
    }

    @Test
    fun test_getRepos_should_callError() {
        val mockedResponse: Throwable = mock()
        val searchKey = "test"

        doReturn(Observable.just(mockedResponse))
                .`when`(api)
                .search(searchKey)

        presenter.getRepos(searchKey)

        testScheduler.triggerActions()

        verify(view).showProgress()
        verify(view).onError()
        verify(view).hideProgress()
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

}