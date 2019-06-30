package com.fitness.fitnesshub

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { ExerciseViewModel(get()) }
    viewModel { IntervalViewModel(get()) }
}

class ExerciseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@ExerciseApplication)
            modules(listOf(appModule))
        }
    }
}