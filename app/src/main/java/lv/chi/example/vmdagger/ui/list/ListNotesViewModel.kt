package lv.chi.example.vmdagger.ui.list

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lv.chi.example.vmdagger.domain.model.Note
import lv.chi.example.vmdagger.domain.repository.NotesRepository
import lv.chi.example.vmdagger.ui.core.AppViewModel
import timber.log.Timber

class ListNotesViewModel(
    private val notesRepository: NotesRepository
) : AppViewModel() {

    val state = State()

    fun list() {
        if (state.loading.get() || state.loaded.get()) return
        state.loading.set(true)

        notesRepository.list()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Notes loaded")
                    state.notes.clear()
                    state.notes.addAll(it)
                    state.loading.set(false)
                    state.loaded.set(true)
                },
                {
                    Timber.e(it, "Failed to load notes")
                    state.loading.set(false)
                }
            )
            .untilCleared()
    }

    class State {
        val notes = ObservableArrayList<Note>()
        val loading = ObservableBoolean()
        val loaded = ObservableBoolean()
    }
}