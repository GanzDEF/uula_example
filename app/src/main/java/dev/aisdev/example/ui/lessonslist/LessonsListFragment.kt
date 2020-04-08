package dev.aisdev.example.ui.lessonslist

import dev.aisdev.example.R
import dev.aisdev.example.di.Scopes
import dev.aisdev.example.presentation.LessonsListFragmentPresenter
import dev.aisdev.example.presentation.LessonsListFragmentView
import dev.aisdev.example.ui.base.BaseListFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick

class LessonsListFragment
    : BaseListFragment<Any, LessonsListFragmentView, LessonsListFragmentPresenter, LessonsAdapter>(),
    LessonsListFragmentView,
    LessonsAdapter.LessonsListener {

    override val layoutRes: Int
        get() = R.layout.layout_base_list

    @InjectPresenter
    lateinit var presenter: LessonsListFragmentPresenter

    @ProvidePresenter
    fun providePresenter(): LessonsListFragmentPresenter = Toothpick
        .openScope(Scopes.SERVER_SCOPE)
        .getInstance(LessonsListFragmentPresenter::class.java)

    override fun initAdapter() = LessonsAdapter(this)

    override fun initPresenter() = presenter

    override fun onVideoClicked(itemId: String) = presenter.onVideoClicked(itemId)

    override fun onOfflineMaterialClicked(itemId: String) =
        presenter.onOfflineMaterialClicked(itemId)

    override fun onSurveyClicked(itemId: String) = presenter.onSurveyClicked(itemId)

    override fun onPrevClicked() = presenter.onPrevClicked()

    override fun onNextClicked() = presenter.onNextClicked()
}