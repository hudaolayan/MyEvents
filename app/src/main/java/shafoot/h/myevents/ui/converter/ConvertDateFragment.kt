/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.converter

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import shafoot.h.myevents.R
import shafoot.h.myevents.common.enums.Language
import shafoot.h.myevents.data.network.Result
import shafoot.h.myevents.data.prefs.Prefs
import shafoot.h.myevents.databinding.FragmentConvertDateBinding
import shafoot.h.myevents.models.Event
import shafoot.h.myevents.ui.base.BaseBindingFragment
import shafoot.h.myevents.utils.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class ConvertDateFragment : BaseBindingFragment<FragmentConvertDateBinding>() {

    override val layoutId: Int = R.layout.fragment_convert_date

    private val viewModel: ConvertDateViewModel by activityViewModels()

    lateinit var mDatePickerDialog: DatePickerDialog

    @Inject
    lateinit var prefs: Prefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::mDatePickerDialog.isInitialized) {
            initDatePicker()
        }
        initListeners()

    }

    private fun initDatePicker() {
        binding?.dateTxt?.setText(getTodayDate())
        getHijriDate(binding?.dateTxt?.text.toString())
        val newCalendar: Calendar = Calendar.getInstance()
        mDatePickerDialog = DatePickerDialog(
            requireActivity(),
            { _, year, monthOfYear, dayOfMonth ->
                val newDate: Calendar = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat(D_M_Y_DATE_FORMAT, Locale.ENGLISH)
                val startDate = newDate.time
                val formattedDate: String = dateFormat.format(startDate)
                binding?.dateTxt?.setText(formattedDate)
                getHijriDate(formattedDate)
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun initListeners() {
        binding?.dateTxt?.setOnClickListener {
            mDatePickerDialog.show()
        }
        binding?.errorView?.tryAgainBtn?.setOnClickListener {
            getHijriDate(binding?.dateTxt?.text?.toString())
        }
        binding?.addEventBtn?.setOnClickListener {
            findNavController().navigate(
                ConvertDateFragmentDirections.addEditEventFragmentDirection(
                    Event(
                        gregorianDate = binding?.dateTxt?.text?.toString(),
                        hijriDate = binding?.hijriDateTxt?.text?.toString(),
                        serverDatetime = binding?.dateTxt?.text?.toString()?.getTimeMillis(
                            D_M_Y_DATE_FORMAT
                        ) ?: 0
                    )
                )
            )
        }
    }

    private fun getHijriDate(gregorianDate: String?) {
        viewModel.getHijriDate(gregorianDate)
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Result.Loading -> {
                        if (it.show) {
                            binding?.errorView?.hide()
                            binding?.progressBar?.show()
                            binding?.hijriDate = null
                        } else {
                            binding?.progressBar?.hide()
                        }
                    }
                    is Result.Success -> {
                        binding?.progressBar?.hide()
                        binding?.hijriDate = it.data?.data?.hijriDate
                        it.data?.data?.hijriDate?.month?.let { month ->
                            if (prefs.language == Language.English.value) {
                                binding?.hijriMonth = month.en
                            } else {
                                binding?.hijriMonth = month.ar
                            }
                        }
                    }
                    is Result.Error -> {
                        binding?.progressBar?.hide()
                        binding?.hijriDataGroup?.hide()
                        binding?.errorView?.apply {
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