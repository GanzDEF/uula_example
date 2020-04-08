package dev.aisdev.example.model.data.db.repositories

import dev.aisdev.example.entities.lesson.LessonData
import dev.aisdev.example.model.converters.LessonsApiConverter
import dev.aisdev.example.model.data.db.LessonsDatabase
import dev.aisdev.example.model.data.server.UulaApi
import dev.aisdev.example.model.data.system.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LessonsRepository @Inject constructor(
    private val api: UulaApi,
    private val converter: LessonsApiConverter,
    private val schedulers: SchedulersProvider,
    private val database: LessonsDatabase
){

    fun getLessonsByPageId(pageId: Int) = when (pageId) {
        0 -> getLessonsFromApi()
        else -> getLessonsFromApiByPageId(pageId)
    }

//        Single.create<List<LessonData>> {
//        getLessonsFromApiByPageId(pageId)
//            .subscribeOn(schedulers.io())
//            .observeOn(schedulers.ui())
//            .map { list ->
//                var newList = mutableListOf<LessonData>()
//                if (!list.isNullOrEmpty()) {
//                    list.map { newList.add(converter.from(it).copy(page_id = pageId)) }
//                    Observable.just(insertLessonsDataToDataBase(newList))
//                        .subscribeOn(schedulers.io())
//                        .observeOn(schedulers.ui())
//                        .subscribe()
//                    newList.toList()
//                } else Observable.just(getLessonsListFromDatabaseById(pageId))
//                    .subscribeOn(schedulers.io())
//                    .observeOn(schedulers.ui())
//                    .subscribe({
//                        newList = it.toMutableList()
////                        return@subscribe newList.toList()
//                        newList.toList()
//                    }, {})
//            }
//    }

//        when (pageId){
//         0 -> getLessonsFromApi()
//             .subscribeOn(schedulers.io())
//             .observeOn(schedulers.ui())
//             .map { list ->
//                 if (!list.isNullOrEmpty()) {
//                     val newList = mutableListOf<LessonData>()
//                     list.map { newList.add(converter.from(it).copy(page_id = pageId)) }
//                     Observable.just(insertLessonsDataToDataBase(newList))
//                         .subscribeOn(schedulers.io())
//                         .observeOn(schedulers.ui())
//                         .subscribe()
//                      return  Single.create { newList }
//                 } else  Observable.just(getLessonsListFromDatabaseById(pageId))
//                     .subscribeOn(schedulers.io())
//                     .observeOn(schedulers.ui())
//                     .subscribe({
//                          it
//                     },{})
//             }
//        else -> getLessonsFromApiByPageId(pageId)
//            .subscribeOn(schedulers.io())
//            .observeOn(schedulers.ui())
//            .map { list ->
//                if (!list.isNullOrEmpty()) {
//                    val newList = mutableListOf<LessonData>()
//                    list.map { newList.add(converter.from(it).copy(page_id = pageId)) }
//                    Observable.just(insertLessonsDataToDataBase(newList))
//                        .subscribeOn(schedulers.io())
//                        .observeOn(schedulers.ui())
//                        .subscribe()
//                    newList
//                } else  Observable.just(getLessonsListFromDatabaseById(pageId))
//                    .subscribeOn(schedulers.io())
//                    .observeOn(schedulers.ui())
//                    .subscribe({
//                        it
//                    },{})
//            }
//    }

    private fun insertLessonsDataToDataBase(list: List<LessonData>) =
         database.lessonsDAO().insertLessons(list)

    private fun getLessonsListFromDatabaseById(pageId: Int) =
         database.lessonsDAO().getLessons(pageId)

    private fun getLessonsFromApi() =
        api.getLessonsList()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .map { it }

    private fun getLessonsFromApiByPageId(id: Int) =
        api.getLessonsListByPageId(id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .map { it }
}




