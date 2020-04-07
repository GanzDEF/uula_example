package dev.aisdev.example.ui.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.aisdev.example.R
import dev.aisdev.example.extentions.checkSelfPermissions
import dev.aisdev.example.extentions.copyToBuffer
import dev.aisdev.example.extentions.toast
import dev.aisdev.example.extentions.visible
import dev.aisdev.example.presentation.base.BaseView
import moxy.MvpAppCompatActivity

@Suppress("unused", "UNUSED_PARAMETER")
@SuppressLint("Registered")
abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    companion object {
        const val REQUEST_CODE_PERMISSIONS = 333
        const val BASE_APP_BAR_ID = R.id.toolbar
    }

    open val appBarId: Int = BASE_APP_BAR_ID
    abstract val layoutResId: Int
    open var isPortrait: Boolean = true
    open var isFullscreen: Boolean = false

    private var permissionsForRequest: Pair<String, Pair<() -> Unit, () -> Unit>>? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isPortrait) requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (isFullscreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        if (layoutResId != 0) setContentView(layoutResId)

        findViewById<Toolbar>(appBarId)?.apply {
            setSupportActionBar(this)
            title = " "
            setNavigationOnClickListener { onBackPressed() }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    decorView.systemUiVisibility = decorView.systemUiVisibility or
                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }

                statusBarColor = ContextCompat.getColor(context, R.color.black_20)
                navigationBarColor = ContextCompat.getColor(context, R.color.black_20)
            }
        }
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        overrideConfiguration?.run {
            val tmpUiMode = uiMode
            setTo(baseContext.resources.configuration)
            uiMode = tmpUiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun showViews(vararg views: View, show: Boolean, hideType: Int = View.GONE) =
        views.forEach { it.visible(show) }

    override fun showMessage(msg: String) {
        toast(msg)
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

    fun withPermission(permissions: Array<String>, granted: () -> Unit, denied: () -> Unit) {
        when {
            applicationContext.checkSelfPermissions(permissions.toList()) -> granted()
            else -> permissions.map { permission ->
                permissionsForRequest = Pair(permission, Pair(granted, denied))
                ActivityCompat.requestPermissions(this,  arrayOf(permission), REQUEST_CODE_PERMISSIONS)
            }
        }
    }

    override fun refreshScreen() {
        startActivity(intent)
        exit()
    }

    override fun exit() = finish()

    override fun copyTextToBuffer(text: String) = copyToBuffer(text)
}