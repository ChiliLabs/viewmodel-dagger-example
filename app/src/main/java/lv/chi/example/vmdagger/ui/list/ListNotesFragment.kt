package lv.chi.example.vmdagger.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import lv.chi.example.vmdagger.BR
import lv.chi.example.vmdagger.R
import lv.chi.example.vmdagger.databinding.FragmentListNotesBinding
import lv.chi.example.vmdagger.domain.model.Note
import lv.chi.example.vmdagger.ui.core.AppFragment
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

class ListNotesFragment : AppFragment(),
    OnNoteClickListener {

    @Inject
    lateinit var vm: ListNotesViewModel

    override fun onStart() {
        super.onStart()
        vm.list()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentListNotesBinding.inflate(inflater, container, false)
            .apply {
                listener = this@ListNotesFragment
                state = this@ListNotesFragment.vm.state
                notesbinding = ItemBinding
                    .of<Note>(BR.note, R.layout.view_note_item)
                    .bindExtra(BR.listener, this@ListNotesFragment)
                executePendingBindings()
            }
            .root

    fun addNote() {
        navController().navigate(ListNotesFragmentDirections.ActionListNotesToAddNote())
    }

    override fun onNoteClicked(note: Note) {
        navController().navigate(ListNotesFragmentDirections.ActionListNotesToViewNote(note))
    }
}