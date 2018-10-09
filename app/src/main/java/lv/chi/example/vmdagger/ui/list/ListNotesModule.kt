package lv.chi.example.vmdagger.ui.list

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
    ListNotesModule.ProvideViewModel::class
])
abstract class ListNotesModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): ListNotesFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(ListNotesViewModel::class)
        fun provideListNotesViewModel(notesRepository: NotesRepository): ViewModel =
            ListNotesViewModel(notesRepository)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideListNotesViewModel(
            factory: ViewModelProvider.Factory,
            target: ListNotesFragment
        ): ListNotesViewModel =
            ViewModelProviders.of(target, factory).get(ListNotesViewModel::class.java)
    }

}