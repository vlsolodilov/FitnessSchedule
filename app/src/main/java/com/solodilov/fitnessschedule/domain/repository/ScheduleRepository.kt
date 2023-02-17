package com.solodilov.fitnessschedule.domain.repository

import com.solodilov.fitnessschedule.domain.entity.Lesson

interface ScheduleRepository {

    suspend fun getLessonList(): List<Lesson>
}