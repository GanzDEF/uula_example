package dev.aisdev.example.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.recyclerview.widget.RecyclerView
import dev.aisdev.example.entities.LessonData
import dev.aisdev.example.entities.Resource
import dev.aisdev.example.extentions.bindResource
import dev.aisdev.example.ui.adapters.LessonsDataAdapter

@BindingConversion
fun bindingVisibile(visible: Boolean) =
    if (visible) View.VISIBLE else View.GONE

@Suppress("CAST_NEVER_SUCCEEDS")
@BindingAdapter("adapterLessonsData")
fun bindAdapterLessonsData(view: RecyclerView, resource: Resource<LessonData>?) {
    resource?.let {
        view.bindResource(it) {
            val adapter = view.adapter as? LessonsDataAdapter

        }
    }
}