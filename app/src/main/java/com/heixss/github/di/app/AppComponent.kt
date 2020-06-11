package com.heixss.github.di.app

import com.heixss.github.GithubApp
import com.heixss.github.di.modules.ActivityBindingModule
import com.heixss.github.di.modules.AppModule
import com.heixss.github.di.modules.FragmentBindingModule
import com.heixss.github.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        FragmentBindingModule::class,
        ActivityBindingModule::class]
)
interface AppComponent {
    
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: GithubApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: GithubApp)
}