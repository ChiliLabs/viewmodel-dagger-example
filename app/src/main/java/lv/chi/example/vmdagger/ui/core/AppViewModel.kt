package lv.chi.example.vmdagger.ui.core

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AppViewModel : ViewModel() {

    private val disposeOnClear = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeOnClear.clear()
    }

    fun Disposable.untilCleared() =
        disposeOnClear.add(this)

}