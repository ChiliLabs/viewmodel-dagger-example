package lv.chi.example.vmdagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import lv.chi.example.vmdagger.data.DataModule
import lv.chi.example.vmdagger.ui.UiModule
import javax.inject.Singleton

@Component(modules = [
    DataModule::class,
    AndroidSupportInjectionModule::class,
    UiModule::class
])
@Singleton
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}