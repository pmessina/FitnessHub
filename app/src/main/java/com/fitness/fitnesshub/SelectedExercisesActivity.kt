package com.fitness.fitnesshub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
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

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setUpExerciseList(exerciseViewModel)

    }

    private fun setUpExerciseList(exerciseViewModel: ExerciseViewModel) {
        try {
            //val selectedExerciseAdapter = SelectedExerciseAdapter(exerciseViewModel)

            val group = arrayListOf<ExerciseGroup>()

            val selectedExercises = exerciseViewModel.selectedExercises

            selectedExercises.forEach {
                val intervals = exerciseViewModel.getIntervals(it.id!!)
                if (intervals.isEmpty()){
                    group.add(ExerciseGroup(it, arrayListOf(Interval(0, 0, 0, 0, 0))))
                }
                else {
                    group.add(ExerciseGroup(it, intervals))
                }
            }

            val animator = rvSelectedExerciseList.itemAnimator as DefaultItemAnimator
            animator.supportsChangeAnimations = false

            val rvLayoutManager = LinearLayoutManager(this)
            val orientation = rvLayoutManager.orientation
            val itemDecoration = DividerItemDecoration(this@SelectedExercisesActivity, orientation)
            val selectedExerciseAdapter = ExpandableExerciseAdapter(group, exerciseViewModel)
            rvSelectedExerciseList.apply {
                setHasFixedSize(true)
                layoutManager = rvLayoutManager
                adapter = selectedExerciseAdapter
                addItemDecoration(itemDecoration)
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
