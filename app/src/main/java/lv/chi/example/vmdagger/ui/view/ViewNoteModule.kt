package lv.chi.example.vmdagger.ui.view

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewNoteModule {

    @ContributesAndroidInjector
    abstract fun bind(): ViewNoteFragment

}