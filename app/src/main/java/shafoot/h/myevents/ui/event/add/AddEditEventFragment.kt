/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.event.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import shafoot.h.myevents.R
import shafoot.h.myevents.data.network.Result
import shafoot.h.myevents.databinding.FragmentAddEditEventBinding
import shafoot.h.myevents.ui.base.BaseBindingBottomSheetDialogFragment
import shafoot.h.myevents.ui.event.EventViewModel
import shafoot.h.myevents.utils.hide
import shafoot.h.myevents.utils.show
import shafoot.h.myevents.utils.showToast

@AndroidEntryPoint
class AddEditEventFragment : BaseBindingBottomSheetDialogFragment<FragmentAddEditEventBinding>() {

    override val layoutId: Int = R.layout.fragment_add_edit_event

    private val args: AddEditEventFragmentArgs by navArgs()

    private val viewModel: EventViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initEvent(args.event)
        binding?.viewModel = viewModel
        initListeners()
        observeData()
    }

    private fun initListeners() {
        binding?.cancelBtn?.setOnClickListener {
            dismiss()
        }
    }

    private fun observeData() {
        viewModel.addEditEvent.observe(viewLifecycleOwner, {
            if (!it.hasBeenHandled) {
                when (val content = it.getContentIfNotHandled()) {
                    is Result.Loading -> {
                        if (content.show) {
                            binding?.progressBar?.show()
                        } else {
                            binding?.progressBar?.hide()
                        }
                    }
                    is Result.Success -> {
                        binding?.progressBar?.hide()
                        dismiss()
                        requireContext().showToast(getString(R.string.save_success))
                    }
                    is Result.Error -> {
                        requireContext().showToast(getString(content.error.messageResource))
                    }
                }
            }
        })

        viewModel.message.observe(this, { event ->
            event.getContentIfNotHandled()?.let {
                requireContext().showToast(getString(it))
            }
        })
    }

    companion object {

    }

}