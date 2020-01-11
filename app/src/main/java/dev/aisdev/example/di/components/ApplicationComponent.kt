package dev.aisdev.example.di.components

import dagger.Component
import dev.aisdev.example.model.interactors.LessonsListInteractor
import dev.aisdev.example.db.repositories.LessonsRoomRepository
import javax.inject.Singleton

@Component(modules = [])
@Singleton
interface ApplicationComponent {
    fun inject(interactor: LessonsListInteractor)
    fun inject(repository: LessonsRoomRepository)

}