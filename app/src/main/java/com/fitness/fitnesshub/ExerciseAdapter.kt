package com.fitness.fitnesshub

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.multiselector.MultiSelector
import com.bignerdranch.android.multiselector.SwappingHolder
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import java.util.ArrayList

class ExerciseHolder(val v: View, val selector: MultiSelector, private val exerciseViewModel: ExerciseViewModel) :
    SwappingHolder(v, selector) {

    constructor(v: View, exerciseViewModel: ExerciseViewModel)
            : this(v, MultiSelector(), exerciseViewModel)

    var textView = v as TextView

    fun setSelectedExercisesListener() {
        textView.setOnClickListener {

            val position = v.tag as Int

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
        textView.setOnClickListener { view ->

            val position = view.tag as Int
            val intent = Intent(view.context, IntervalActivity::class.java)

            intent.putExtra("exercise", exerciseViewModel.selectedExercises[position])

            view.context.startActivity(intent)
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
        holder.textView.text = exerciseViewModel.exerciseList[position].name
        holder.textView.tag = position
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
        holder.textView.text = exerciseViewModel.selectedExercises[position].name
        holder.textView.tag = position
    }
}

data class ExerciseGroup(val exercise: Exercise, val intervals: MutableList<Interval>) :
    ExpandableGroup<Interval>(exercise.name, intervals)

data class ExerciseGroupViewHolder(val v: TextView) : GroupViewHolder(v) {

    fun setGroupTitle(group: ExpandableGroup<in Interval>) {

        v.text = group.getTitle()
    }
}

data class IntervalViewHolder(val tvInterval: TextView) : ChildViewHolder(tvInterval) {
    fun onBind(interval: Interval) {
        tvInterval.text = interval.toString()
    }
}

class ExpandableExerciseAdapter(groups: MutableList<out ExerciseGroup>) : ExpandableRecyclerViewAdapter<ExerciseGroupViewHolder, IntervalViewHolder>(groups) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ExerciseGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_exercise_item, parent, false) as TextView
        return ExerciseGroupViewHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): IntervalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_exercise_item, parent, false) as TextView
        return IntervalViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: IntervalViewHolder?, flatPosition: Int, group: ExpandableGroup<in Interval>, childIndex: Int) {
        val interval = group.getItems() as MutableList<in Interval>
        holder!!.tvInterval.text = interval[childIndex].toString()
    }

    override fun onBindGroupViewHolder(holder: ExerciseGroupViewHolder?, flatPosition: Int, group: ExpandableGroup<in Interval>) {
        holder!!.setGroupTitle(group)
    }


}


