package com.fitness.fitnesshub

import androidx.room.*

@Dao
interface ExerciseDao {
    @Query("select * from exercise where name = :name")
    fun getExerciseByName(name: String): Exercise

    @Query("select * from exercise")
    fun getAll(): Array<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    @Query("select * from exercise where id = :id")
    fun getExerciseById(id: Int): Exercise

    @Query("delete from exercise WHERE id = :id")
    fun removeExercise(id: Int)

    @Update
    fun updateExercise(exercise: Exercise)

    @Insert
    fun insertInterval(interval: Interval)

    @Query("select * from interval where exerciseId = :exerciseId")
    fun getIntervalsById(exerciseId: Int): List<Interval>

    @Insert
    fun insertAll(exercises: Array<Exercise>)

    @Query("delete from exercise")
    fun removeAll()

    @Query("SELECT COUNT('id') FROM exercise")
    fun getNumberOfRows(): Int
}