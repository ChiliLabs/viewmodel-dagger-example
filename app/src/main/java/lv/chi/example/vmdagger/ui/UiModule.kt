package lv.chi.example.vmdagger.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import lv.chi.example.vmdagger.ui.add.AddNoteModule
import lv.chi.example.vmdagger.ui.core.AppViewModelFactory
import lv.chi.example.vmdagger.ui.list.ListNotesModule
import lv.chi.example.vmdagger.ui.view.ViewNoteModule
import javax.inject.Provider

@Module(includes = [
    ListNotesModule::class,
    AddNoteModule::class,
    ViewNoteModule::class
])
class UiModule {

    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory =
        AppViewModelFactory(providers)
}