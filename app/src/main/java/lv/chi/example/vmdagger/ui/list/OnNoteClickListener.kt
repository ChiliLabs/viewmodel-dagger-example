package lv.chi.example.vmdagger.ui.list

import lv.chi.example.vmdagger.domain.model.Note

interface OnNoteClickListener {
    fun onNoteClicked(note: Note)
}