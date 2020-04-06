package dev.aisdev.example.model.data.system

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
    fun newThread(): Scheduler
}