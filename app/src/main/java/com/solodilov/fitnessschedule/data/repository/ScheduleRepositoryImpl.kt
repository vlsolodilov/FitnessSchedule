package com.solodilov.fitnessschedule.data.repository

import com.solodilov.fitnessschedule.data.datasource.ScheduleDatasource
import com.solodilov.fitnessschedule.domain.entity.Lesson
import com.solodilov.fitnessschedule.domain.repository.ScheduleRepository
import java.time.LocalDate
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val dataSource: ScheduleDatasource,
) : ScheduleRepository {

    override suspend fun getLessonList(): List<Lesson> {
        val schedule = dataSource.getSchedule()
        return schedule.lessons.map { lessonDto ->
            Lesson(
                id = lessonDto.appointmentId,
                name = lessonDto.name,
                location = lessonDto.place,
                starTime = lessonDto.startTime,
                endTime = lessonDto.endTime,
                date = try {
                    LocalDate.parse(lessonDto.date)
                } catch (e: Exception) {
                    LocalDate.MAX
                },
                coachName = schedule.trainers
                    .firstOrNull { lessonDto.coachId == it.id }?.fullName ?: "",
                color = lessonDto.color,
            )
        }
    }
}