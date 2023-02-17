package com.solodilov.fitnessschedule.presentation.screens.lessons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodilov.fitnessschedule.domain.entity.Lesson
import com.solodilov.fitnessschedule.domain.usecase.GetLessonListUseCase
import com.solodilov.fitnessschedule.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.solodilov.fitnessschedule.presentation.common.Result
import com.solodilov.fitnessschedule.presentation.screens.lessons.adapter.model.ScheduleUi
import java.time.format.DateTimeFormatter

class LessonsViewModel @Inject constructor(
    private val getLessonListUseCase: GetLessonListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ScheduleUi>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ScheduleUi>>> = _uiState

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            flow {
                emit(Result.Loading)
                try {
                    emit(Result.Success(mapLessonListToScheduleList(getLessonListUseCase())))
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
            }.collect { result ->
                _uiState.value = when (result) {
                    is Result.Loading -> UiState.Loading
                    is Result.Success -> UiState.Success(result.data)
                    is Result.Error -> UiState.Error(result.exception)
                }
            }
        }
    }

    private fun mapLessonListToScheduleList(lessons: List<Lesson>): List<ScheduleUi> {
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM")
        val scheduleList = mutableListOf<ScheduleUi>()
        lessons
            .groupBy { it.date }
            .toSortedMap()
            .forEach { entry ->
                scheduleList.add(ScheduleUi.DateUi(entry.key.format(formatter)))
                scheduleList.addAll(entry.value.map { mapLessonToLessonUi(it) })
            }

        return scheduleList
    }

    private fun mapLessonToLessonUi(lesson: Lesson): ScheduleUi.LessonUi =
        ScheduleUi.LessonUi(
            id = lesson.id,
            name = lesson.name,
            location = lesson.location,
            starTime = lesson.starTime,
            endTime = lesson.endTime,
            coachName = lesson.coachName,
            color = lesson.color,
        )

}