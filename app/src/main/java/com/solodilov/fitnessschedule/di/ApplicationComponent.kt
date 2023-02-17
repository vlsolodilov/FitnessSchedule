package com.solodilov.fitnessschedule.di

import android.app.Application
import com.solodilov.fitnessschedule.presentation.screens.lessons.LessonsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
	DataModule::class,
	NetworkModule::class,
	ViewModelModule::class,
])
interface ApplicationComponent {

	fun inject(fragment: LessonsFragment)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application,
		): ApplicationComponent
	}
}