package com.fitness.fitnesshub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
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
            //val selectedExerciseList = intent.getSerializableExtra("exercises") as ArrayList<Exercise>

            val selectedExerciseAdapter = SelectedExerciseAdapter(exerciseViewModel)

            rvSelectedExerciseList.apply{
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@SelectedExercisesActivity)
                adapter = selectedExerciseAdapter
            }


        } catch (ex: Exception) {
            Log.d(TAG, ex.message)
        }



    }

}
