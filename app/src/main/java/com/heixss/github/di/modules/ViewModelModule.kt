package com.heixss.github.di.modules

import androidx.lifecycle.ViewModel
import com.heixss.github.di.app.ViewModelKey
import com.heixss.github.viewmodels.RepoDetailsViewModel
import com.heixss.github.viewmodels.ReposViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReposViewModel::class)
    abstract fun bindReposViewModel(viewModel: ReposViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailsViewModel::class)
    abstract fun bindRepoDetailsFragment(viewModel: RepoDetailsViewModel): ViewModel
}
