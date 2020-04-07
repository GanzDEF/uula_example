package dev.aisdev.example.ui.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.aisdev.example.extentions.checkSelfPermissions
import dev.aisdev.example.extentions.copyToBuffer
import dev.aisdev.example.extentions.showToast
import dev.aisdev.example.presentation.base.BaseView
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    companion object {
        const val REQUEST_CODE_PERMISSIONS = 333
    }

    abstract val layoutRes: Int

    private var permissionsForRequest: Pair<String, Pair<() -> Unit, () -> Unit>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (savedInstanceState ?: arguments)?.let { initData(it) }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutRes, container, false)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveData(outState)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (permissionsForRequest != null && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val granted = permissionsForRequest?.second?.first
                if (granted != null) {
                    granted()
                }
            } else {
                val denied = permissionsForRequest?.second?.second
                if (denied != null) {
                    denied()
                }
            }
        }
    }

    fun withPermission(permission: String, granted: () -> Unit, denied: () -> Unit) {
        if (context?.checkSelfPermissions(listOf(permission)) == true) {
            granted()
        } else {
            permissionsForRequest = Pair(permission, Pair(granted, denied))
            requestPermissions(
                arrayOf(permission),
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun showMessage(msg: String) {
        context?.showToast(msg)
    }

    override fun copyTextToBuffer(text: String) {
        context?.copyToBuffer(text)
    }

    open fun initData(args: Bundle) { /*ignore*/ }
    open fun saveData(outState: Bundle) {/*ignore*/}
    override fun exit() { activity?.onBackPressed() }
}