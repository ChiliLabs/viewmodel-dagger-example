package lv.chi.example.vmdagger.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import lv.chi.example.vmdagger.databinding.FragmentViewNoteBinding
import lv.chi.example.vmdagger.ui.core.AppFragment

class ViewNoteFragment : AppFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentViewNoteBinding.inflate(inflater, container, false)
            .apply {
                note = ViewNoteFragmentArgs.fromBundle(arguments).note
            }
            .root

}