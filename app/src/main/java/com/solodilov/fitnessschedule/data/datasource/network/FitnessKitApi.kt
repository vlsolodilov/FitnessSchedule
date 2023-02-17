package com.solodilov.fitnessschedule.data.datasource.network

import com.solodilov.fitnessschedule.data.model.ScheduleDto
import retrofit2.http.GET

interface FitnessKitApi {

    @GET("schedule/get_v3/?club_id=2")
    suspend fun getSchedule(): ScheduleDto

}