package com.solodilov.fitnessschedule.di

import com.solodilov.fitnessschedule.data.datasource.ScheduleDatasource
import com.solodilov.fitnessschedule.data.datasource.ScheduleDatasourceImpl
import com.solodilov.fitnessschedule.data.repository.ScheduleRepositoryImpl
import com.solodilov.fitnessschedule.domain.repository.ScheduleRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

	@Singleton
	@Binds
	fun bindScheduleDatasource(impl: ScheduleDatasourceImpl): ScheduleDatasource

	@Singleton
	@Binds
	fun bindScheduleRepository(impl: ScheduleRepositoryImpl): ScheduleRepository

}