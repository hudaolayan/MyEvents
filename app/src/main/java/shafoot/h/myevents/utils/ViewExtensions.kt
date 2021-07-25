/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.utils


import android.view.View
import androidx.viewbinding.ViewBinding

fun ViewBinding?.show() {
    this?.root?.visibility = View.VISIBLE
}

fun ViewBinding?.hide() {
    this?.root?.visibility = View.GONE
}

fun ViewBinding?.invisible() {
    this?.root?.visibility = View.INVISIBLE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}