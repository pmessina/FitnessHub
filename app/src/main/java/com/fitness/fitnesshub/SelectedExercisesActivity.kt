package com.fitness.fitnesshub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.multiselector.MultiSelector
import kotlinx.android.synthetic.main.activity_selected_exercises.*
import kotlinx.android.synthetic.main.content_selected_exercises.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

class SelectedExercisesActivity : AppCompatActivity() {

    private val TAG = SelectedExercisesActivity::class.simpleName

    private val exerciseViewModel: ExerciseViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_exercises)

        //setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//        selectedExerciseViewModel = ViewModelProviders.of(this).get(SelectedExerciseViewModel::class.java)
        //val exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)


//        dao = ExerciseDatabase.getINSTANCE(this).exerciseDao()
//
//        recyclerView = findViewById(R.id.rvSelectedExerciseList)
//
//        recyclerView.setHasFixedSize(true)
//        recyclerView.setLayoutManager(LinearLayoutManager(this))
//
//        exerciseAdapter = SelectedExerciseAdapter(this)


//        if (savedInstanceState != null) {
//            selectedExerciseList = (ArrayList<Exercise>) savedInstanceState.getSerializable("exercises");
//        }

        setUpExerciseList(exerciseViewModel)

    }

    private fun setUpExerciseList(exerciseViewModel: ExerciseViewModel) {
        try {
            //val selectedExerciseAdapter = SelectedExerciseAdapter(exerciseViewModel)

            val group = mutableListOf<ExerciseGroup>()

            exerciseViewModel.selectedExercises.forEach { ex ->
                group.add(ExerciseGroup(ex, mutableListOf(Interval(0, 0, 20, 2, 50),
                Interval(1, 0, 30, 2, 25),
                Interval(2, 0, 40, 2, 10))))
            }

            val animator = rvSelectedExerciseList.itemAnimator as DefaultItemAnimator
            animator.supportsChangeAnimations = false

            val selectedExerciseAdapter = ExpandableExerciseAdapter(group)
            rvSelectedExerciseList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@SelectedExercisesActivity)
                adapter = selectedExerciseAdapter

            }

        } catch (ex: Exception) {
            Log.d(TAG, ex.message)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> exerciseViewModel.selectedExercises.clear()
        }

        return super.onOptionsItemSelected(item)
    }

}
