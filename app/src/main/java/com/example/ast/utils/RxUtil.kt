package com.dbnow.hw.utils


import io.reactivex.disposables.Disposable

object RxUtil {

    fun unsubscribe(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }
}
