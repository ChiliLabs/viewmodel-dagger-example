package lv.chi.example.vmdagger.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey
    val id: String,
    val text: String?,
    val added: Long
) : Parcelable