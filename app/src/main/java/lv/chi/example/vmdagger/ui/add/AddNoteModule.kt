package lv.chi.example.vmdagger.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import lv.chi.example.vmdagger.domain.repository.NotesRepository
import lv.chi.example.vmdagger.ui.core.di.ViewModelKey

@Module(includes = [
    AddNoteModule.ProvideViewModel::class
])
abstract class AddNoteModule {

    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    abstract fun bind(): AddNoteFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(AddNoteViewModel::class)
        fun provideAddNoteViewModel(notesRepository: NotesRepository): ViewModel =
            AddNoteViewModel(notesRepository)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideAddNoteViewModel(
            factory: ViewModelProvider.Factory,
            target: AddNoteFragment
        ) = ViewModelProviders.of(target, factory).get(AddNoteViewModel::class.java)
    }

}