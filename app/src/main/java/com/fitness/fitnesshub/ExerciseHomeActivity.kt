package com.fitness.fitnesshub

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.multiselector.MultiSelector
import kotlinx.android.synthetic.main.activity_exercise_home.*
import kotlinx.android.synthetic.main.content_exercise.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class ExerciseHomeActivity : AppCompatActivity() {//, OnExerciseSelectedListener {

    //private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_treadmill -> {
                //textMessage.text = "Workout"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_workout -> {
                //textMessage.text = "Treadmill"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

//    override fun onExerciseSelected(holder: ExerciseHolder, multiSelector: MultiSelector) {
//        if (!multiSelector.isSelectable){
//            multiSelector.isSelectable = true
//            multiSelector.setSelected(holder, true)
//        }

//    }

    private val exerciseViewModel: ExerciseViewModel by inject()

    private val selector = MultiSelector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_home)

        val exerciseRepository = ExerciseRepository(application)
        val exercises = exerciseRepository.getAllExercises()

        exerciseViewModel.exerciseList = exercises

        val exerciseAdapter = ExerciseAdapter(exerciseViewModel)

        rvExercises.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = exerciseAdapter
        }

        btnAddExercise.setOnClickListener { view ->
            val intent = Intent(this, AddExerciseActivity::class.java)
            startActivity(intent)
        }

        btnSubmit.setOnClickListener {
            exerciseViewModel.onClick(it)
        }
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_restore -> {
                ExerciseDatabase.getInstance(this).populateData()
                Toast.makeText(this, "Data restored to default", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        //outState?.putAll(selector.saveSelectionStates())
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()


    }


}


