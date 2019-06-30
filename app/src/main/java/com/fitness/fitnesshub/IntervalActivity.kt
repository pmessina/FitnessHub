package com.fitness.fitnesshub

import android.annotation.TargetApi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_interval.*
import kotlinx.android.synthetic.main.fragment_interval.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

@TargetApi(26)
class IntervalActivity : AppCompatActivity() { //, IntervalViewModel.ExerciseCallback {
//    override val exercise: Exercise
//        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    val exerciseViewModel: ExerciseViewModel by inject()

    val intervalViewModel: IntervalViewModel by viewModel()

    //val exerciseCallback: IntervalViewModel.ExerciseCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interval)

        val exercise = intent.extras?.getSerializable("exercise") as Exercise

        val selExercises = exerciseViewModel.selectedExercises

        val selExercise = selExercises.filter { it == exercise }

        tvExerciseName.text = exercise.name
        tvExerciseDate.text = java.time.LocalDate.now().toString()

//9
//        val intervals = intervalViewModel.getIntervals(exercise.id!!);
//
//        for (interval in intervals) {
//
//            Log.i("interval", interval.toString());
//
        //val fragmentTransaction = supportFragmentManager.beginTransaction()
//            //ExistingIntervalFragment existingIntervalFragment = ExistingIntervalFragment.newInstance();
//
//            //Bundle bundle = Bundle();
//            //bundle.putSerializable("interval", interval);
//
//            //existingIntervalFragment.setArguments(bundle);
//            //fragmentTransaction.add(R.id.existingIntervals, existingIntervalFragment);
//            //fragmentTransaction.commit();
//        }

    }


    fun onFragmentAddClick(v: View) {
        val fragmentTransaction = supportFragmentManager.beginTransaction();

        when (v.id) {
            R.id.btnIntervalSubmit -> {

                val reps = Integer.parseInt(edtReps.text.toString())
                val sets = Integer.parseInt(edtSets.text.toString());
                val weight = Integer.parseInt(edtWeight.text.toString());

                //val selectedExercise = exerciseViewModel.getSelectedExercise(v.id)

                intervalViewModel.setInterval(Exercise(0), reps, sets, weight);
            }

            R.id.imgAddInterval -> {

                val intervalFragment = IntervalFragment.newInstance()

                fragmentTransaction.add(R.id.newIntervals, intervalFragment);

                fragmentTransaction.commit();

            }

        }
    }


}


//        Button btnSubmitInterval = findViewById(R.id.btnIntervalSubmit);
//        btnSubmitInterval.setOnClickListener(this);
//        ImageButton imgAddInterval = findViewById(R.id.imgAddInterval);
//        imgAddInterval.setOnClickListener(this);

//        IntervalDao intervalDao = ExerciseDatabase.getINSTANCE(this).intervalDao();
//        LiveData<List<Interval>> intervals = intervalDao.getIntervalsByExerciseId(exercise.getId());
//
//        intervals.observe(this, new Observer<List<Interval>>() {
//            @Override
//            public void onChanged(@Nullable List<Interval> interval) {
//
//
//            }
//        });


//setUpMultiSliderListener();
//}

//public void setUpMultiSliderListener() {
//        MultiSlider slider = findViewById(R.id.msRange);

//        final TextView tvValue = findViewById(R.id.tvValue);
//        tvValue.setText(String.valueOf(slider.getMin()));

//        slider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
//            @Override
//            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
//                tvValue.setText(String.valueOf(value));
//            }
//        });
//}





