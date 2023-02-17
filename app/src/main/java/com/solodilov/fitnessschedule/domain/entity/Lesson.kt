package com.solodilov.fitnessschedule.domain.entity

import java.time.LocalDate

data class Lesson(
    val id: String,
    val name: String,
    val location: String,
    val starTime: String,
    val endTime: String,
    val date: LocalDate,
    val coachName: String,
    val color: String,
)
