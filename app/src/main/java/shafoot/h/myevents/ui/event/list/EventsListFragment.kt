/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.event.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.util.Preconditions
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import shafoot.h.myevents.R
import shafoot.h.myevents.data.network.Result
import shafoot.h.myevents.databinding.FragmentEventsListBinding
import shafoot.h.myevents.models.DayEvents
import shafoot.h.myevents.models.Event
import shafoot.h.myevents.ui.base.BaseBindingFragment
import shafoot.h.myevents.ui.event.EventViewModel
import shafoot.h.myevents.utils.hide
import shafoot.h.myevents.utils.show
import shafoot.h.myevents.utils.showSnackBarAlert
import javax.inject.Inject


@AndroidEntryPoint
class EventsListFragment : BaseBindingFragment<FragmentEventsListBinding>() {

    override val layoutId: Int = R.layout.fragment_events_list

    @Inject
    lateinit var adapter: DayEventsAdapter

    private val viewModel: EventViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        initListeners()
        getEvents()

    }

    private fun setAdapter() {
        binding?.eventsRv?.adapter = adapter
    }

    private fun initListeners() {
        binding?.errorStateView?.tryAgainBtn?.setOnClickListener {
            getEvents()
        }
        adapter.setOnEventClickListener { view, item, _ ->
            when (view?.id) {
                R.id.editLayout -> {
                    item?.let {
                        findNavController().navigate(
                            R.id.addEditEventFragment,
                            bundleOf("event" to it)
                        )
                    }
                }
                R.id.deleteLayout -> {
                    item?.let { showDeleteEventDialog(it) }
                }
                else -> {

                }
            }
        }

    }

    private fun getEvents() {
        viewModel.getEvents().observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    if (it.show) {
                        binding?.errorStateView?.hide()
                        binding?.emptyStateView?.hide()
                        binding?.progressBar?.show()
                    } else {
                        binding?.progressBar?.hide()
                    }
                }
                is Result.Success -> {
                    val datesList = ArrayList<DayEvents>()
                    it.data?.groupBy { event -> event?.serverDatetime }?.forEach { item ->
                        datesList.add(DayEvents(item.key, item.value))
                    }
                    if (datesList.isNullOrEmpty()) {
                        binding?.emptyStateView?.show()
                        adapter.submitItems(mutableListOf())
                    } else {
                        binding?.errorStateView?.hide()
                        binding?.emptyStateView?.hide()
                        adapter.submitItems(datesList)
                    }
                }
                is Result.Error -> {
                    binding?.progressBar?.hide()
                    binding?.emptyStateView?.hide()
                    if (adapter.items.isNullOrEmpty()) {
                        binding?.errorStateView?.apply {
                            show()
                            tvErrorMessage.text =
                                getString(it.error.messageResource)
                            if (it.error.messageResource == R.string.error_server) {
                                tryAgainBtn.hide()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showDeleteEventDialog(event: Event) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.delete_event_title))
        builder.setMessage(getString(R.string.delete_event_description, event.name))
        val dialog: AlertDialog = builder.create()

        builder.setPositiveButton(R.string.delete) { _, _ ->
            viewModel.deleteEvent(event).observe(viewLifecycleOwner, deleteEventObserver)
            dialog.dismiss()
        }

        builder.setNegativeButton(R.string.cancel) { _, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun showDeleteMultipleEventDialog(events: MutableList<Event?>) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.delete_multiple_event_title, events.size))
        builder.setMessage(getString(R.string.delete_multiple_event_description))
        val dialog: AlertDialog = builder.create()

        builder.setPositiveButton(R.string.delete) { _, _ ->
            viewModel.deleteEvents(events).observe(viewLifecycleOwner, deleteEventObserver)
            dialog.dismiss()
        }

        builder.setNegativeButton(R.string.cancel) { _, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private val deleteEventObserver = Observer<Result<Unit>> {
        when (it) {
            is Result.Loading -> {
                if (it.show) {
                    binding?.progressBar?.show()
                } else {
                    binding?.progressBar?.hide()
                }
            }
            is Result.Success -> {
                binding?.progressBar?.hide()
                binding?.root?.showSnackBarAlert(getString(R.string.deleted_successfully))
            }
            is Result.Error -> {
                binding?.root?.showSnackBarAlert(getString(it.error.messageResource))
            }
        }
    }

}