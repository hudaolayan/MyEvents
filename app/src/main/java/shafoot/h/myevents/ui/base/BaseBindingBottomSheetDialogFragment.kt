/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBindingBottomSheetDialogFragment<V : ViewDataBinding> : BottomSheetDialogFragment() {

    private var mView: View? = null

    protected abstract val layoutId: Int

    protected var binding: V? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            mView = binding?.root
            binding?.lifecycleOwner = this.viewLifecycleOwner
        }

        return mView
    }


}