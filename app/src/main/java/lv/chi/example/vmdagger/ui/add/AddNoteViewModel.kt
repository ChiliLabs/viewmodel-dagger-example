package lv.chi.example.vmdagger.ui.add

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lv.chi.example.vmdagger.domain.model.Note
import lv.chi.example.vmdagger.domain.repository.NotesRepository
import lv.chi.example.vmdagger.ui.core.AppViewModel
import timber.log.Timber
import java.util.*

class AddNoteViewModel(
    private val notesRepository: NotesRepository
) : AppViewModel() {

    val state = State()

    fun save() {
        if (state.loading.get() || state.saved.get()) return
        state.loading.set(true)

        notesRepository.put(
            Note(
                UUID.randomUUID().toString(),
                state.text.get(),
                System.currentTimeMillis()
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Note saved")
                    state.saved.set(true)
                    state.loading.set(false)
                },
                {
                    Timber.e(it, "Failed to add note")
                    state.loading.set(false)
                }
            )
            .untilCleared()
    }

    class State {
        val text = ObservableField<String>()
        val loading = ObservableBoolean()
        val saved = ObservableBoolean()
    }

}