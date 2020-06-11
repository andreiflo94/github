package com.heixss.github.di.modules

import com.heixss.github.ui.fragments.RepoDetailsFragment
import com.heixss.github.ui.fragments.ReposFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindReposFragment(): ReposFragment

    @ContributesAndroidInjector
    abstract fun bindRepoDetailsFragment(): RepoDetailsFragment
}