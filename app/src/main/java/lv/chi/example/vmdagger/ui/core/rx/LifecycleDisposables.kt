package lv.chi.example.vmdagger.ui.core.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class LifecycleDisposables {

    private val disposeOnPause = CompositeDisposable()
    private val disposeOnStop = CompositeDisposable()
    private val disposeOnDestroyView = CompositeDisposable()
    private val disposeOnDestroy = CompositeDisposable()

    internal fun add(state: State, disposable: Disposable) {
        when (state) {
            State.Pause -> disposeOnPause.add(disposable)
            State.Stop -> disposeOnStop.add(disposable)
            State.DestroyView -> disposeOnDestroyView.add(disposable)
            State.Destroy -> disposeOnDestroy.add(disposable)
        }
    }

    internal fun dispose(state: State) {
        when (state) {
            State.Destroy -> {
                disposeOnPause.clear()
                disposeOnStop.clear()
                disposeOnDestroyView.clear()
                disposeOnDestroy.clear()
            }
            State.DestroyView -> {
                disposeOnPause.clear()
                disposeOnStop.clear()
                disposeOnDestroyView.clear()
            }
            State.Stop -> {
                disposeOnPause.clear()
                disposeOnStop.clear()
            }
            State.Pause -> {
                disposeOnPause.clear()
            }
        }
    }

    internal sealed class State {
        object Pause : State()
        object Stop : State()
        object DestroyView : State()
        object Destroy : State()
    }

}