package lv.chi.example.vmdagger.ui.add

import android.os.Bundle
import android.view.*
import io.reactivex.android.schedulers.AndroidSchedulers
import lv.chi.example.vmdagger.R
import lv.chi.example.vmdagger.databinding.FragmentAddNoteBinding
import lv.chi.example.vmdagger.ui.core.AppFragment
import lv.chi.example.vmdagger.ui.core.rx.toFlowable
import javax.inject.Inject

class AddNoteFragment : AppFragment() {

    @Inject
    lateinit var vm: AddNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentAddNoteBinding.inflate(inflater, container, false)
            .apply {
                listener = this@AddNoteFragment
                state = vm.state
            }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.state.saved.toFlowable()
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it }
            .subscribe {
                navController().popBackStack()
            }
            .untilDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_note, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.save)
            .setOnMenuItemClickListener {
                vm.save()
                true
            }
    }

}