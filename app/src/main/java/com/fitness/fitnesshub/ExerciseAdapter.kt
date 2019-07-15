package com.fitness.fitnesshub

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.multiselector.MultiSelector
import com.bignerdranch.android.multiselector.SwappingHolder

class ExerciseHolder(val v: View, val selector: MultiSelector, private val exerciseViewModel: ExerciseViewModel) :
    SwappingHolder(v, selector) {

    constructor(v: View, exerciseViewModel: ExerciseViewModel)
            : this(v, MultiSelector(), exerciseViewModel)

    val tvExerciseInfo:TextView = v.findViewById(R.id.tvExerciseName)
    val tvExerciseMethod:TextView = v.findViewById(R.id.tvExerciseMethod)
    val exerciseRecord:LinearLayout = v.findViewById(R.id.exerciseRecord)

    fun setSelectedExercisesListener() {
        exerciseRecord.setOnClickListener {

            val position = it.tag as Int

            if (!selector.isSelectable) {
                selector.isSelectable = true
                selector.setSelected(this, true)
                this.selectionModeBackgroundDrawable =
                    ContextCompat.getDrawable(v.context, android.R.color.holo_blue_dark)
                this.defaultModeBackgroundDrawable =
                    ContextCompat.getDrawable(v.context, android.R.color.holo_blue_dark)
                exerciseViewModel.selectedExercises.add(exerciseViewModel.exerciseList[position])
            } else {
                selector.isSelectable = false
                selector.setSelected(this, false)

                this.selectionModeBackgroundDrawable = null
                this.defaultModeBackgroundDrawable = null

                exerciseViewModel.selectedExercises.remove(exerciseViewModel.exerciseList[position])
            }

        }

        selector.saveSelectionStates()
    }

    fun setExercisesIntervalListener() {
        tvExerciseInfo.setOnClickListener {

            val position = it.tag as Int
            val intent = Intent(it.context, IntervalActivity::class.java)

            intent.putExtra("exercise", exerciseViewModel.selectedExercises[position])

            it.context.startActivity(intent)
        }
    }


}



open class ExerciseAdapter(val exerciseViewModel: ExerciseViewModel) :
    RecyclerView.Adapter<ExerciseHolder>() {

    override fun getItemCount(): Int {
        return exerciseViewModel.exerciseList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.content_exercise_item, parent, false)
        val selector = MultiSelector()
        val holder = ExerciseHolder(inflater, selector, exerciseViewModel)
        holder.setSelectedExercisesListener()
        return holder
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.tvExerciseInfo.text = exerciseViewModel.exerciseList[position].name
        holder.exerciseRecord.tag = position
        holder.tvExerciseMethod.text = exerciseViewModel.exerciseList[position].method
    }

}

class SelectedExerciseAdapter(exerciseViewModel: ExerciseViewModel) :
    ExerciseAdapter(exerciseViewModel) {

    override fun getItemCount(): Int {
        return exerciseViewModel.selectedExercises.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.content_exercise_item, parent, false)

        val holder = ExerciseHolder(inflater, exerciseViewModel)
        holder.setExercisesIntervalListener()
        return holder
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.tvExerciseInfo.text = exerciseViewModel.selectedExercises[position].name
        holder.tvExerciseInfo.tag = position
    }
}





