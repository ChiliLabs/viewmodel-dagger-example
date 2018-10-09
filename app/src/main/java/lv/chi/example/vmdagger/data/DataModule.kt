package lv.chi.example.vmdagger.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import lv.chi.example.vmdagger.data.db.NotesDao
import lv.chi.example.vmdagger.data.db.NotesDatabase
import lv.chi.example.vmdagger.data.repository.NotesRepositoryImpl
import lv.chi.example.vmdagger.domain.repository.NotesRepository
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideNotesRepository(dao: NotesDao): NotesRepository =
        NotesRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideNotesDao(db: NotesDatabase): NotesDao =
        db.notesDao()

    @Provides
    @Singleton
    fun provideNotesDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "Notes.db"
        )
            .build()

}