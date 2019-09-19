package com.frozens.daily.utils

import android.app.Dialog
import com.frozens.daily.component.network.ExceptionHandle
import com.frozens.daily.component.network.ServerException
import com.frozens.daily.entity.Response
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxUtils {
    companion object {


        fun <T> progressTransformer(
            process: Dialog? = null, page: Int = 1, inIO: Boolean = true
        ): ObservableTransformer<T, T> {
            return ObservableTransformer {
                val temp = if (inIO) {
                    it.subscribeOn(Schedulers.io())
                } else {
                    it
                }
                temp.doOnSubscribe {
                    if (page == 1) {
                        if (process != null && !process.isShowing) {
                            process.show()
                        }
                    }

                }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
            }

        }


/*        fun <T> progressTransformer(
            progress: Dialog? = null, refreshLayout: SmartRefreshLayout? = null,
            doOnSubscribe: (() -> (Unit))? = null, page: Int = 1
            , needSubIo: Boolean = true
        ): ObservableTransformer<T, T> {

            return ObservableTransformer {
                val temp = if (needSubIo) {
                    it.subscribeOn(Schedulers.io())
                } else {
                    it
                }
                temp
                    .doOnSubscribe {
                        if (page == 1) {
                            if (progress != null && !progress.isShowing) {
                                progress.show()
                            }
                            refreshLayout?.simpleStarRefresh()
                            doOnSubscribe?.invoke()
                        }
                    }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())

            }
        }

        */

        fun <T> threadTransformer(
            firstThread: Scheduler,
            finishSchedulers: Scheduler
        ): ObservableTransformer<T, T> {

            return ObservableTransformer {
                it
                    .subscribeOn(firstThread)
                    .observeOn(finishSchedulers)


            }
        }


        fun <T> errorTransformer(): ObservableTransformer<Response<T>, Response<T>> {
            return ObservableTransformer { tResponse ->
                tResponse.flatMap {
                    if (!it.isOk()) {
                        val exception =
                            ExceptionHandle.handleException(ServerException(it.code, it.msg))
                        throw exception
                    }

                    Observable.just(it)
                }
            }
        }


/*        fun <T : Response<T>> cacheErrorTransformer(): ObservableTransformer<T, T> {
            return ObservableTransformer { tResponse ->
                tResponse.flatMap {
                    if (!it.isOK()) {
                        throw ServerException(it.code, it.msg)
                    }
                    Observable.just(it)
                }
            }
        }*/

        //单位秒
        fun getCountDownObservable(time: Int): Observable<Int> {
            val countTime = if (time < 0) 0 else time
            return Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .take((countTime + 1).toLong())
                .map {
                    countTime - it.toInt()
                }
        }
    }
}


