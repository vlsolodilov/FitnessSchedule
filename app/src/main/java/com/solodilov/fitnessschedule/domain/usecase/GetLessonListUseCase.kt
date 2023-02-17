package com.solodilov.fitnessschedule.domain.usecase

import com.solodilov.fitnessschedule.domain.entity.Lesson
import com.solodilov.fitnessschedule.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetLessonListUseCase @Inject constructor(private val scheduleRepository: ScheduleRepository) {

    suspend operator fun invoke(): List<Lesson> =
        scheduleRepository.getLessonList()
}