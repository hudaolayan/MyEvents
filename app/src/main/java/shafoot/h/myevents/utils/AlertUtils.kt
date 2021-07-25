/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBarErrorAlert(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .show()
}

fun View.showSnackBarAlert(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}