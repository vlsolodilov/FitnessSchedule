package com.solodilov.fitnessschedule.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solodilov.fitnessschedule.presentation.common.ViewModelFactory
import com.solodilov.fitnessschedule.presentation.screens.lessons.LessonsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LessonsViewModel::class)
    fun bindLessonsViewModel(viewModel: LessonsViewModel): ViewModel

}