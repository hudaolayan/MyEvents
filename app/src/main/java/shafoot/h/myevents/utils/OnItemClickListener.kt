/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.utils

import android.view.View

fun interface OnItemClickListener<T> {
    fun onItemClicked(view: View?, item: T?, position: Int)
}
