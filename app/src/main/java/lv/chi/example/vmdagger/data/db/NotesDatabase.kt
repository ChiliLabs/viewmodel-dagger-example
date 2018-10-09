package lv.chi.example.vmdagger.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import lv.chi.example.vmdagger.domain.model.Note

@Database(
    entities = [
        Note::class
    ],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

}