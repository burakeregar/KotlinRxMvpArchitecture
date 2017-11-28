package com.base.mvp

import com.base.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by Burak Eregar on 20.05.2017.
 * burakeregar@gmail.com
 * https://github.com/burakeregar
 */

open class BasePresenter<V : BaseView> constructor(var disposable: CompositeDisposable, var scheduler: SchedulerProvider) : Presenter<V> {

    private var weakReference: WeakReference<V>? = null

    override fun attachView(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
            view.setPresenter(this)
        }
    }

    override fun detachView() {
        weakReference?.clear()
        weakReference = null
        disposable.clear()
    }

    val view: V?
        get() = weakReference?.get()

    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null


}
