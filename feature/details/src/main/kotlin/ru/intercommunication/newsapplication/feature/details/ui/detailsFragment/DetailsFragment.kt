package ru.intercommunication.newsapplication.feature.details.ui.detailsFragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.core.utils.AlarmScheduler
import ru.intercommunication.newsapplication.feature.details.R
import ru.intercommunication.newsapplication.feature.details.databinding.FragmentDetailsBinding
import ru.intercommunication.newsapplication.feature.details.di.storage.DetailsComponentStorage
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.details.domain.models.ReminderTime
import ru.intercommunication.newsapplication.feature.details.ui.bottomReminder.ReminderBottomSheetFragment
import ru.intercommunication.newsapplication.feature.details.ui.bottomReminder.SelectReminder
import javax.inject.Inject

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("binding not must be null")

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { _: Boolean ->

    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val detailsViewModel: DetailsViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var alarmScheduler: AlarmScheduler<ArticleModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        ViewModelProvider(this).get<DetailsComponentStorage>()
            .component
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("id")?.let {
            detailsViewModel.getNews(it)
        } ?: throw RuntimeException("id not must be null")

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailsViewModel.news.collect {
                    changeView(it)
                }
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.commentValue.doAfterTextChanged {
            binding.commentValue.isCursorVisible = true
            binding.saveButton.isVisible = it.toString() != detailsViewModel.news.value.comment
        }

        binding.saveButton.setOnClickListener {
            detailsViewModel.saveComment(binding.commentValue.text.toString())
            binding.commentValue.isCursorVisible = false
        }

        binding.reminderTitle.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val isNotificationPermissionGranted = ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                if (!isNotificationPermissionGranted) {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    startBottomReminder()
                }
            } else {
                startBottomReminder()
            }
        }
    }

    private fun startBottomReminder() {
        val selectReminderListenerImpl = object : SelectReminder {
            override fun select(reminder: ReminderTime) {
                detailsViewModel.saveReminder(reminder)
                changeReminder(reminder)
                when (reminder) {
                    ReminderTime.NOTHING -> alarmScheduler.cancel(detailsViewModel.news.value)
                    else -> alarmScheduler.schedule(
                        detailsViewModel.news.value,
                        reminder.millisecond
                    )
                }
            }
        }

        ReminderBottomSheetFragment(selectReminderListenerImpl).show(
            childFragmentManager,
            "reminder_bottom"
        )
    }

    private fun changeView(articleModel: ArticleModel) {

        with(binding) {
            Glide.with(this@DetailsFragment)
                .load(articleModel.urlToImage)
                .placeholder(R.drawable.newspaper_img)
                .centerCrop()
                .into(imgNews)

            collapsingToolbar.title = articleModel.title

            descriptionValue.text = articleModel.description
            descriptionValue.isVisible = articleModel.description.isNotEmpty()
            contentValue.text = articleModel.content
            contentValue.isVisible = articleModel.content.isNotEmpty()
            commentValue.setText(articleModel.comment)

            changeReminder(articleModel.reminder)

        }
    }

    private fun changeReminder(
        reminder: ReminderTime
    ) {
        binding.reminderValue.text = when (reminder) {
            ReminderTime.FIFTEEN_MINUTE -> getString(R.string.fifteen_minute)
            ReminderTime.HOUR -> getString(R.string.hour)
            ReminderTime.DAY -> getString(R.string.day)
            ReminderTime.SEVEN_DAY -> getString(R.string.seven_day)
            ReminderTime.NOTHING -> getString(R.string.nothing_reminder)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}