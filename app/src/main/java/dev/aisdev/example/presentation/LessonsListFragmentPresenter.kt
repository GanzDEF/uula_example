package dev.aisdev.example.presentation

import dev.aisdev.example.R
import dev.aisdev.example.entities.lesson.LessonData
import dev.aisdev.example.entities.lesson.LessonFooter
import dev.aisdev.example.entities.lesson.LessonHeader
import dev.aisdev.example.entities.lesson.LessonKind
import dev.aisdev.example.model.converters.LessonDataToVideoConverter
import dev.aisdev.example.model.converters.LessonsDataToOfflineMaterial
import dev.aisdev.example.model.converters.LessonsDataToSurveyConverter
import dev.aisdev.example.model.data.system.ResourceManager
import dev.aisdev.example.model.interactors.LessonsListInteractor
import dev.aisdev.example.presentation.base.BaseListPresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LessonsListFragmentPresenter @Inject constructor(
    private val interactor: LessonsListInteractor,
    private val videoConverter: LessonDataToVideoConverter,
    private val surveyConverter: LessonsDataToSurveyConverter,
    private val offlineMaterialConverter: LessonsDataToOfflineMaterial,
    private val rm: ResourceManager
) : BaseListPresenter<Any, LessonsListFragmentView>(){

    private val pages = mutableListOf<Int>().apply { for(i in 1..10){ add(i) } } as List<Int>
    private var currentPage = pages.first()
    private var footerItem = LessonFooter(prev = false, next =  false)

    override fun attachView(view: LessonsListFragmentView?) {
        super.attachView(view)
        checkOtherListsAvailability()
    }

    override fun loadList(page: Int) {
        viewState.showProgress()
        if (currentPage in pages) {
            interactor.getLessonsList(page)
                .subscribe({ convertList(it) },{ it.processError(); viewState.hideProgress() })
                .connect()
        } else  convertList(listOf())
    }

    private fun checkOtherListsAvailability() {
        footerItem.next = (currentPage + 1) in pages
        footerItem.prev = (currentPage -1 ) in pages
    }

    private fun convertList(list: List<LessonData>) {
        viewState.refreshScreen()
        checkOtherListsAvailability()
        val completed = String.format("%d/%d", list.count { it.visited == true }, list.count())
        val newList = mutableListOf<Any>()
        newList.add(0, LessonHeader(title = completed))
        list.map {
            newList.add(
                when (it.kind) {
                    LessonKind.VIDEO -> videoConverter.from(it)
                    LessonKind.OFFLINE_MATERIAL -> offlineMaterialConverter.from(it)
                    LessonKind.SURVEY -> surveyConverter.from(it)
                    else -> return
                }
            )
        }
        newList.add(newList.lastIndex + 1, footerItem)
        applyList(newList)
        viewState.hideProgress()
    }

    fun onPrevClicked() {
        currentPage -= 1
        if (currentPage in pages) {
            refresh()
        }
    }

    fun onNextClicked() {
        currentPage += 1
        if (currentPage in pages) {
            refresh()
        }
    }

    fun onVideoClicked(itemId: String) =
        viewState.showMessage(String.format(rm.getString(R.string.lesson_list_clicked_video), itemId))

    fun onOfflineMaterialClicked(itemId: String) =
        viewState.showMessage(String.format(rm.getString(R.string.lessons_list_clicked_offline_material), itemId))

    fun onSurveyClicked(itemId: String) =
        viewState.showMessage(String.format(rm.getString(R.string.lessons_list_survey_clicked), itemId))
}
