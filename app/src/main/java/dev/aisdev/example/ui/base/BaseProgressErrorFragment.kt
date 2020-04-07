package dev.aisdev.example.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.aisdev.example.R
import dev.aisdev.example.extentions.visible
import dev.aisdev.example.presentation.base.BaseProgressErrorView

abstract class BaseProgressErrorFragment : BaseFragment(), BaseProgressErrorView {

    private lateinit var contentContainer: ViewGroup
    private lateinit var errorContainer: ViewGroup
    private lateinit var progressContainer: ViewGroup

    companion object {

        const val CONTENT_CONTAINER_ID = R.id.vgContentContainer
        const val ERROR_CONTAINER_ID = R.id.vgErrorContainer
        const val PROGRESS_CONTAINER_ID = R.id.vgProgressContainer

        const val ROOT_LAYOUT = R.layout.layout_root_container
        const val ERROR_LAYOUT = R.layout.layout_error_layout
        const val PROGRESS_LAYOUT = R.layout.layout_progress_loading
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(ROOT_LAYOUT, container, false)

        contentContainer = rootView.findViewById(CONTENT_CONTAINER_ID)
        errorContainer = rootView.findViewById(ERROR_CONTAINER_ID)
        progressContainer = rootView.findViewById(PROGRESS_CONTAINER_ID)

        val contentView = onCreateContentView(inflater, contentContainer, savedInstanceState)
        val errorView = onCreateErrorView(inflater, errorContainer, savedInstanceState)
        val progressView = onCreateProgressView(inflater, progressContainer, savedInstanceState)

        contentContainer.addView(contentView)
        errorContainer.addView(errorView)
        progressContainer.addView(progressView)

        errorContainer.visible(false)
        progressContainer.visible(false)

        return rootView
    }

    open fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = super.onCreateView(inflater, container, savedInstanceState)

    open fun onCreateProgressView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(PROGRESS_LAYOUT, container, false)

    open fun onCreateErrorView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(ERROR_LAYOUT, container, false)

    override fun showProgress() {
        progressContainer.visible(true)
        errorContainer.visible(false)
    }

    override fun hideProgress() {
        progressContainer.visible(false)
    }

    override fun showError() {
        errorContainer.visible(true)
        progressContainer.visible(false)
    }

    override fun hideError() {
        errorContainer.visible(false)
    }
}
