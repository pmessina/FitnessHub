package com.fitness.fitnesshub


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_interval.*


class IntervalFragment : Fragment(), View.OnClickListener {

    lateinit var exerciseCallback: IntervalViewModel.ExerciseCallback

    companion object {

        fun newInstance(): IntervalFragment {
            return IntervalFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_interval, container, false);

//        val currentExercise = exerciseCallback.exercise
//
//        val interval = arguments?.getSerializable("interval") as Interval?
//
//        edtReps.setText(interval!!.reps)
//        edtSets.setText(interval!!.sets);
//        edtWeight.setText(interval.sets);


        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        fragmentManager?.putFragment(outState, "IntervalFragment", this);
    }


    override fun onClick(v: View) {
        fragmentManager?.beginTransaction()?.detach(this)?.commit();
    }

}
