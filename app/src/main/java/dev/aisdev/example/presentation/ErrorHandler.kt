package dev.aisdev.example.presentation

import dev.aisdev.example.BuildConfig
import dev.aisdev.example.R
import dev.aisdev.example.model.data.server.ServerError
import dev.aisdev.example.model.data.system.ResourceManager
import dev.aisdev.example.model.data.system.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val schedulers: SchedulersProvider,
    private val resourceManager: ResourceManager
) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun handle(error: Throwable, doLogOut: Boolean = true, messageListener: (String) -> Unit): Disposable? {
        if (error is ServerError) {
            when (error.error) {

            }
        } else {
            if (BuildConfig.DEBUG) {
                messageListener(error.message.toString())
            } else {
                messageListener(resourceManager.getString(R.string.common_errors_offline))
            }
        }
        return null
    }

    fun onDestroy() {
        disposables.dispose()
    }

}