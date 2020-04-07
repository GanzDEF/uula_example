package dev.aisdev.example.presentation.base

import dev.aisdev.example.di.Scopes
import dev.aisdev.example.presentation.ErrorHandler
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import toothpick.Toothpick

@Suppress("MemberVisibilityCanBePrivate")
abstract class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    private val compositeDisposable = CompositeDisposable()
    val errorHandler: ErrorHandler by lazy { Toothpick
        .openScope(Scopes.SERVER_SCOPE)
        .getInstance(ErrorHandler::class.java)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        errorHandler.onDestroy()
        super.onDestroy()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }

    protected fun Completable.start() = subscribe().connect()

    protected fun Single<*>.start() = subscribe().connect()

    fun Throwable.processError() {
        printStackTrace()
        errorHandler.handle(this) { viewState.showMessage(it) }
    }


}