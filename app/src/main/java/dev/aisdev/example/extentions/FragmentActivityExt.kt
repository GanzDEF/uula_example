package dev.aisdev.example.extentions

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

@Suppress("unused")
fun <T : ViewModel> FragmentActivity.vmps(
    factory: ViewModelProvider.Factory,
    model: KClass<T>
): T = ViewModelProviders
    .of(this, factory)
    .get(model.java)