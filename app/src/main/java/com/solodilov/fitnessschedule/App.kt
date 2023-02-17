package com.solodilov.fitnessschedule

import android.app.Application
import com.solodilov.fitnessschedule.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.factory().create(this)
}