package lv.chi.example.vmdagger.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import lv.chi.example.vmdagger.domain.model.Note

interface NotesRepository {

    fun list(): Flowable<List<Note>>

    fun put(note: Note): Completable

}