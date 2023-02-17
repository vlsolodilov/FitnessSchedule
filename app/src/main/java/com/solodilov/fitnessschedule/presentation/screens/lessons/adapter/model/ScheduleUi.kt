package com.solodilov.fitnessschedule.presentation.screens.lessons.adapter.model

sealed class ScheduleUi: ItemUi {
    data class DateUi(
        val value: String,
    ) : ScheduleUi() {
        override val itemId: String = "0"
    }
    data class LessonUi(
        val id: String,
        val name: String,
        val location: String,
        val starTime: String,
        val endTime: String,
        val coachName: String,
        val color: String,
    ) : ScheduleUi() {
        override val itemId: String = id
    }
}

interface ItemUi {
    val itemId: String
}
