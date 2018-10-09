package lv.chi.example.vmdagger.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import lv.chi.example.vmdagger.data.db.NotesDao
import lv.chi.example.vmdagger.domain.model.Note
import lv.chi.example.vmdagger.domain.repository.NotesRepository

class NotesRepositoryImpl(
    private val dao: NotesDao
) : NotesRepository {

    override fun list(): Flowable<List<Note>> =
        dao.list()
            .subscribeOn(Schedulers.io())

    override fun put(note: Note): Completable =
        Completable
            .fromAction {
                dao.insert(note)
            }
            .subscribeOn(Schedulers.io())
}