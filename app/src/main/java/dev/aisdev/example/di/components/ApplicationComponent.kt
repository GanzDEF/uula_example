package dev.aisdev.example.di.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.aisdev.example.di.modules.ApplicationModule
import dev.aisdev.example.di.modules.ViewModelModule
import dev.aisdev.example.di.modules.WebModule
import dev.aisdev.example.model.data.db.repositories.LessonsRepository
import dev.aisdev.example.model.interactors.LessonsListInteractor
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class, WebModule::class, ViewModelModule::class])
@Singleton
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(interactor: LessonsListInteractor)
    fun inject(repository: LessonsRepository)

}