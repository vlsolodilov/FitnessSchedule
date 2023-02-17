package com.solodilov.fitnessschedule.presentation.screens.lessons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solodilov.fitnessschedule.databinding.ItemDateBinding
import com.solodilov.fitnessschedule.databinding.ItemLessonBinding
import com.solodilov.fitnessschedule.presentation.screens.lessons.adapter.model.ScheduleUi

class ScheduleAdapter(
) : ListAdapter<ScheduleUi, RecyclerView.ViewHolder>(ScheduleDiffUtilItemCallback()) {

    private companion object {
        const val DATE_VIEW_TYPE = 0
        const val LESSON_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LESSON_VIEW_TYPE -> LessonViewHolder(ItemLessonBinding
                .inflate(LayoutInflater.from(parent.context), parent, false))
            DATE_VIEW_TYPE -> DateViewHolder(ItemDateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Wrong viewType: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateViewHolder -> holder.bind(currentList[position] as ScheduleUi.DateUi)
            is LessonViewHolder -> holder.bind(currentList[position] as ScheduleUi.LessonUi)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is ScheduleUi.DateUi -> DATE_VIEW_TYPE
            is ScheduleUi.LessonUi -> LESSON_VIEW_TYPE
        }

}

class LessonViewHolder(
    private val binding: ItemLessonBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lesson: ScheduleUi.LessonUi) {

        with(binding) {
            startTime.text = lesson.starTime
            endTime.text = lesson.endTime
            lessonName.text = lesson.name
            coachName.text = lesson.coachName
            location.text = lesson.location
            lessonColor.setBackgroundColor(lesson.color.toColorInt())
        }
    }
}

class DateViewHolder(
    private val binding: ItemDateBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(date: ScheduleUi.DateUi) {
        binding.lessonDate.text = date.value
    }
}

private class ScheduleDiffUtilItemCallback : DiffUtil.ItemCallback<ScheduleUi>() {

    override fun areItemsTheSame(oldItem: ScheduleUi, newItem: ScheduleUi): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: ScheduleUi, newItem: ScheduleUi): Boolean =
        oldItem == newItem
}