package dev.aisdev.example.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.aisdev.example.di.ViewModelKey
import dev.aisdev.example.presentation.LessonsListViewModel
import dev.aisdev.example.presentation.ViewModelProviderFactory
import javax.inject.Singleton

@Module
internal abstract class ViewModelModule {

    @Provides
    @Singleton
    fun provideLessonsListActivityViewModel(): ViewModel = LessonsListViewModel()

    @Provides
    @Singleton
    fun bindViewModelFactory(): ViewModelProvider.Factory = ViewModelProviderFactory()
}