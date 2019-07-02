package com.fitness.fitnesshub

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import java.util.concurrent.Executors

//class ExerciseRepository(){
//
//    val db = FirebaseFirestore.getInstance()
//
//    fun addExercise(name: String, type: String, muscleGroup: String){
//
//        val exercise = hashMapOf<String, Any>( "name" to name, "type" to type, "muscleGroup" to muscleGroup )
//
//
//        db.collection("exercises")
//            .add(exercise)
//            .addOnSuccessListener {
//                    documentReference -> Log.d(TAG,"document added with id ${documentReference.id}")
//            }
//            .addOnFailureListener{
//                    error -> Log.w(TAG, "error adding document", error)
//            }
//    }
//
//    fun getExerciseTypes(){
//
//
//        db.collection("exercises").document("exercise_types").get()
//            .addOnSuccessListener {
//            listener -> Log.d("getExerciseTypes", "getting exercise types: ${listener["type"]}")
//        }
//            .addOnFailureListener{
//                listener -> Log.d("getExerciseTypes", "failed to get exercise type ${listener.stackTrace}")
//            }
//    }
//
//    fun getExercise(): Task<DocumentSnapshot> {
//        return db.collection("exercises").document("4E1jkq9vNUoVWW2vMize")
//            .get()
//    }
//
//    fun getExercises(): Task<QuerySnapshot> {
//        return db.collection("exercises").get()
//    }
//
//}

class ExerciseRepository(application: Application) {
    private var exerciseDao: ExerciseDao? = null

    init {
        try {
            val exerciseDatabase = ExerciseDatabase.getInstance(application)
            exerciseDao = exerciseDatabase.exerciseDao()
        }
      catch (ex: Exception){
          Log.d(TAG, ex.message)
      }
        //val allExercises = exerciseDao.getAll()
    }

    fun getAllExercises() : ArrayList<Exercise>{
        return exerciseDao!!.getAll().toList() as ArrayList<Exercise>
    }

    fun insertExercise(exercise: Exercise) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute { exerciseDao!!.insertExercise(exercise) }
    }

    fun getExercise(id: Int): Exercise {
        return exerciseDao!!.getExerciseById(id)
    }

    fun insertInterval(interval: Interval) {
        exerciseDao!!.insertInterval(interval)
    }

    fun getIntervals(exerciseId: Int): List<Interval> {
        return exerciseDao!!.getIntervals(exerciseId)
    }
}
