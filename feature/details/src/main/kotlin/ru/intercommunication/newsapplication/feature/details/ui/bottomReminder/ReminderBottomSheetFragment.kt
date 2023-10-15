package ru.intercommunication.newsapplication.feature.details.ui.bottomReminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.intercommunication.newsapplication.feature.details.databinding.BottomSheetDioFragmentReminderBinding
import ru.intercommunication.newsapplication.feature.details.domain.models.ReminderTime


interface SelectReminder {
    fun select(reminder: ReminderTime)
}

class ReminderBottomSheetFragment(private val selectReminderListener: SelectReminder) : BottomSheetDialogFragment() {


    private var _binding: BottomSheetDioFragmentReminderBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("binding not must be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDioFragmentReminderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            day.setOnClickListener {
                selectReminderListener.select(ReminderTime.DAY)
                dismiss()
            }

            fifteenMinute.setOnClickListener {
                selectReminderListener.select(ReminderTime.FIFTEEN_MINUTE)
                dismiss()
            }

            hour.setOnClickListener {
                selectReminderListener.select(ReminderTime.HOUR)
                dismiss()
            }

            sevenDay.setOnClickListener {
                selectReminderListener.select(ReminderTime.SEVEN_DAY)
                dismiss()
            }

            nothing.setOnClickListener {
                toggleButtonGroup.clearChecked()
                selectReminderListener.select(ReminderTime.NOTHING)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}