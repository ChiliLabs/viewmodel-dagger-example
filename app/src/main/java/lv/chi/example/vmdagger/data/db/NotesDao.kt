package lv.chi.example.vmdagger.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import lv.chi.example.vmdagger.domain.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun list(): Flowable<List<Note>>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(note: Note): Long

}