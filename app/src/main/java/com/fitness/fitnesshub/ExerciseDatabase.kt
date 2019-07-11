package com.fitness.fitnesshub

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [Exercise::class, Interval::class], version = 5, exportSchema = false)
abstract class ExerciseDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    abstract fun intervalDao(): IntervalDao

    fun populateData() {
        val rows = exerciseDao().getNumberOfRows()

        if (rows == 0) {
            this.beginTransaction()

            val exercises = arrayOf(
                Exercise(null,"Bicep Curls", "Biceps", "Barbells"),
                Exercise(null,"Tricep Extension", "Triceps", "Barbells"),
                Exercise(null,"Skull Crushers", "Triceps", "Barbells"),
                Exercise(null,"Leg Press", "Quadriceps", "Machine"),
                Exercise(null,"Back Extension", "Back", "Barbells"),
                Exercise(null,"Shoulder Press", "Shoulders", "Barbells"),
                Exercise(null,"Bent over Row", "Shoulders", "Barbells"),
                Exercise(null,"Front Raises", "Shoulders", "Barbells"),
                Exercise(null,"Lateral Raises", "Shoulders", "Barbells"),
                Exercise(null,"Reverse Bridge Dips", "Triceps", "Weightless"),
                Exercise(null,"Pushups", "Chest", "Weightless"),
                Exercise(null,"Chest Fly", "Chest", "Barbells"),
                Exercise(null,"One Arm Pushup Left/Right", "Abs", "Weightless"),
                Exercise(null,"Hi Lo Plank", "Abs", "Weightless"),
                Exercise(null,"High Side Planks Left/Right", "Arms", "Weightless"),
                Exercise(null,"Reverse Plank Dips", "Triceps", "Weightless")
            )


            exerciseDao().insertAll(exercises)
            setTransactionSuccessful()
            this.endTransaction()
        }
        else{
            this.beginTransaction()
            exerciseDao().removeAll()
            populateData()
            setTransactionSuccessful()
            this.endTransaction()
        }
    }

    companion object {
        private var INSTANCE: ExerciseDatabase? = null

            fun getInstance(context: Context): ExerciseDatabase {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, ExerciseDatabase::class.java, "exercise_db")
                    .addCallback(object : Callback() {
                        override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadScheduledExecutor().execute {
                                getInstance(context).exerciseDao().removeAll();
                            }
                        }

                    })

                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                    //INSTANCE!!.populateData()

                }
                return INSTANCE as ExerciseDatabase
            }
        }

    }
