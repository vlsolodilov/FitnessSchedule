package com.solodilov.fitnessschedule.presentation.screens.lessons

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.solodilov.fitnessschedule.App
import com.solodilov.fitnessschedule.R
import com.solodilov.fitnessschedule.databinding.FragmentLessonsBinding
import com.solodilov.fitnessschedule.presentation.common.*
import com.solodilov.fitnessschedule.presentation.screens.lessons.adapter.ScheduleAdapter
import com.solodilov.fitnessschedule.presentation.screens.lessons.adapter.model.ScheduleUi
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.onSuccess

class LessonsFragment: Fragment(R.layout.fragment_lessons) {
    private val binding by viewBinding(FragmentLessonsBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: LessonsViewModel by viewModels { viewModelFactory }

    private var scheduleAdapter: ScheduleAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        collectFlow(viewModel.uiState, ::handleState)
    }

    private fun initViews() {
        scheduleAdapter = ScheduleAdapter()
        binding.lessonList.adapter = scheduleAdapter

        binding.errorLayout.tryButton.setOnClickListener { viewModel.getData() }
    }

    private fun handleState(state: UiState<List<ScheduleUi>>) = with(binding) {
        progressBar.isVisible = state is UiState.Loading
        errorLayout.root.isVisible = state is UiState.Error
        lessonList.isVisible = state is UiState.Success

        state
            .onSuccess { data -> scheduleAdapter?.submitList(data) }
            .onError { error -> showToast(error.message.toString()) }
    }

    override fun onDestroyView() {
        scheduleAdapter = null
        super.onDestroyView()
    }
}