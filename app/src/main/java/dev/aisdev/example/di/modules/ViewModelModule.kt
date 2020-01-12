package dev.aisdev.example.di.modules

import androidx.lifecycle.ViewModel
import dagger.Module
import dev.aisdev.example.presentation.LessonsListViewModel

@Module
internal abstract class ViewModelModule {

    internal  abstract fun bindLessonsListActivityViewModel(lessonsListViewModel: LessonsListViewModel): ViewModel
}