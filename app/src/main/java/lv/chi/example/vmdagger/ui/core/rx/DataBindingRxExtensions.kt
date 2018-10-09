package lv.chi.example.vmdagger.ui.core.rx

import androidx.databinding.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

fun <T> ObservableField<T>.toFlowable(): Flowable<T> = toFlowable { this.get() }

fun ObservableInt.toFlowable(): Flowable<Int> = toFlowable { this.get() }

fun ObservableBoolean.toFlowable(): Flowable<Boolean> = toFlowable { this.get() }

private fun <T> BaseObservable.toFlowable(getValueFunc: () -> T) = Flowable.create<T>({
    val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(dataBindingObservable: Observable, propertyId: Int) {
            it.onNext(getValueFunc())
        }
    }
    addOnPropertyChangedCallback(callback)
    it.setCancellable {
        removeOnPropertyChangedCallback(callback)
    }
}, BackpressureStrategy.LATEST)