package com.solodilov.fitnessschedule.data.datasource

import com.solodilov.fitnessschedule.data.model.ScheduleDto

interface ScheduleDatasource {

    suspend fun getSchedule(): ScheduleDto
}