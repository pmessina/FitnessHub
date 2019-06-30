package com.fitness.fitnesshub

import android.app.Application
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.bignerdranch.android.multiselector.MultiSelector
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayList

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private var exerciseRepository: ExerciseRepository

    var exerciseList = arrayListOf<Exercise>()

    //private var exerciseAdapter: ExerciseAdapter

    private val MAX_SELECTED_EXERCISES = 100

    val selectedExercises = arrayListOf<Exercise>()

    //internal var listener: SelectedExerciseListener? = null

    private var currentDate: String? = null

    val selector = MultiSelector()

    init {
        exerciseList = ArrayList()
        //exerciseAdapter = ExerciseAdapter(exerciseList.toTypedArray())
        exerciseRepository = ExerciseRepository(application)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
        }
    }

    fun insertExercise(exercise: Exercise) {
        exerciseRepository.insertExercise(exercise)
    }

    fun getSelectedExercise(exercise: Exercise): Exercise {

        return exerciseRepository.getExercise(exercise.id!!)
    }

//    fun getSelectedExerciseAt(id: Int?): Exercise {
//        return exerciseAdapter.getExerciseList().get(id)
//    }

    fun onClick(view: View) {

        val intent = Intent(view.context, SelectedExercisesActivity::class.java)
        intent.putExtra("exercises", selectedExercises)
        view.context.startActivity(intent)

        //        activity.getSupportFragmentManager()
        //                .beginTransaction()
        //                .replace(R.id.contentExercise, new SelectedExerciseFragment())
        //                .commit();


        //ArrayList<Exercise> selectedExercises = new ArrayList<>(selectedExercisesAdapter.getExerciseList());


        //        switch (view.getId())
        //        {
        //            case R.id.btnSubmit:
        //                Intent intent = new Intent(this, IntervalActivity.class);
        //                Bundle data = new Bundle();
        //                data.putSerializable("selectedExercises", selectedExercises);
        //
        //                startActivity(intent);
        //                break;
        //        }
    }


}

class IntervalViewModel(application: Application) : AndroidViewModel(application) {
    private val exerciseRepository: ExerciseRepository

    var callback: FragmentCallback? = null

    private var exercise: Exercise? = null

    var exerciseCallback: ExerciseCallback? = null

    init {
        exerciseRepository = ExerciseRepository(application)
    }

    fun setInterval(exercise: Exercise, reps: Int, sets: Int, weight: Int) {
        val interval = Interval(0, exercise.id!!, reps, sets, weight)

        exerciseRepository.insertInterval(interval)
    }


    fun getIntervals(id: Int): List<Interval> {
        return exerciseRepository.getIntervals(id)
    }

    fun setExercise(exercise: Exercise) {
        this.exercise = exercise
    }

    //    @Bindable
    //    public TextWatcher getRepsTextWatcher() {
    //        return new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //                // Do nothing.
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence s, int start, int before, int count) {
    //                //setRepsString(s.toString());
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable s) {
    //                // Do nothing.
    //            }
    //        };
    //    }

    fun onIntervalClick(v: View, exerciseCallback: ExerciseCallback, interval: Interval) {

        val exercise = exerciseCallback.exercise
        //interval.exerciseId = exercise.id!!
        exerciseRepository.insertInterval(interval)

        Toast.makeText(v.context, "Interval Saved", Toast.LENGTH_SHORT).show()
    }

//    fun onAddIntervalClick(v: View) {
//
//        val mCallback: FragmentCallback
//
//        mCallback.onFragmentAddClick(v)
//    }

    interface FragmentCallback {
        fun addFragment(v: View)
    }

    interface ExerciseCallback {
        val exercise: Exercise
    }
}
