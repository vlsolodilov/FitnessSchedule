package com.solodilov.fitnessschedule.data.datasource

import com.solodilov.fitnessschedule.data.datasource.network.FitnessKitApi
import com.solodilov.fitnessschedule.data.model.ScheduleDto
import javax.inject.Inject

class ScheduleDatasourceImpl @Inject constructor(
    private val api: FitnessKitApi,
): ScheduleDatasource {

    override suspend fun getSchedule(): ScheduleDto =
        api.getSchedule()
}