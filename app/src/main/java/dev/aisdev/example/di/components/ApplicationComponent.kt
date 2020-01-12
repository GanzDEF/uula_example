package dev.aisdev.example.di.components

import dagger.Component
import dev.aisdev.example.model.interactors.LessonsListInteractor
import dev.aisdev.example.model.data.db.repositories.LessonsRepository
import dev.aisdev.example.di.modules.ApplicationModule
import dev.aisdev.example.di.modules.WebModule
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class, WebModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(interactor: LessonsListInteractor)
    fun inject(repository: LessonsRepository)

}