/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<V : ViewDataBinding> : BaseActivity() {

    protected lateinit var binding: V

    override fun setContentView(layoutResID: Int) {
        binding = DataBindingUtil.setContentView(this, layoutResID)
    }
}
