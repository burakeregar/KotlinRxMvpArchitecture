package com.base.mvp

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.util.TestSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Burak Eregar on 18.11.2017.
 * burakeregar@gmail.com
 * https://github.com/burakeregar
 */
class BasePresenterTest {

    private lateinit var basePresenter: BasePresenter<BaseView>
    private val view: BaseView = mock()

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        val testSchedulerProvider = TestSchedulerProvider(TestScheduler())
        basePresenter = BasePresenter(compositeDisposable, testSchedulerProvider)
    }

    @Test
    fun attachView() {
        basePresenter.attachView(view)

        verify(view).setPresenter(basePresenter)
    }

    @After
    fun tearDown(){
        basePresenter.detachView()
    }
}