package dev.aisdev.example.extentions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Process
import android.widget.Toast

fun Context.checkSelfPermissions(permissions: List<String>) =
    permissions.all {
        checkPermission(it, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED
    }

fun Context.showToast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT,
    otherContext: Context? = null
) =
    Toast.makeText(otherContext ?: this, message, duration).show()

fun Context.copyToBuffer(text: String) =
    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .setPrimaryClip(ClipData.newPlainText("Copied Text", text))