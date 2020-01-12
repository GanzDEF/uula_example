package dev.aisdev.example.extentions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dev.aisdev.example.entities.Resource
import dev.aisdev.example.entities.Status

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.toast(message: String): Toast = Toast
    .makeText(this,
        message,
        Toast.LENGTH_SHORT)
    .apply { show() }


fun RecyclerView.bindResource(resource: Resource<Any>, onSuccess: () -> Unit) {
    when (resource.status) {
        Status.LOADING -> Unit
        Status.ERROR -> context.toast(resource.message.toString())
        Status.SUCCESS -> onSuccess
    }
}

