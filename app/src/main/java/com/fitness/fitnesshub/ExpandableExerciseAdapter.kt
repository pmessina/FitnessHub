package com.fitness.fitnesshub

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder

data class ExerciseGroup(val exercise: Exercise, val intervals: List<Interval>) :
    ExpandableGroup<Interval>(exercise.name, intervals)

data class ExerciseGroupViewHolder(val view: View, val exerciseViewModel: ExerciseViewModel) : GroupViewHolder(view) {

    val tvExercise:TextView = view.findViewById(R.id.tvExerciseGroup)
    val image: ImageView = view.findViewById(R.id.list_item_exercise_arrow)
    val btnAddInterval: ImageButton = view.findViewById(R.id.btnAddInterval)

    fun setGroupTitle(group: ExpandableGroup<in Interval>) {
        tvExercise.text = group.getTitle()
    }

    override fun expand() {
        animateExpand()
    }

    override fun collapse() {
        animateCollapse()
    }

    private fun animateExpand() {
        val rotate = RotateAnimation(180f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 300
        rotate.fillAfter = true
        image.animation = rotate
    }

    private fun animateCollapse() {
        val rotate = RotateAnimation(360f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 300
        rotate.fillAfter = true
        image.animation = rotate
    }

    fun setExercisesIntervalListener(flatPosition: Int) {

        btnAddInterval.setOnClickListener { view ->

            val intent = Intent(view.context, IntervalActivity::class.java)

            intent.putExtra("exercise", exerciseViewModel.selectedExercises[flatPosition])

            view.context.startActivity(intent)
        }
    }

}

data class IntervalViewHolder(val view: View) : ChildViewHolder(view) {

    private val tvInterval:TextView = view.findViewById(R.id.tvInterval)

    fun bind(interval: Interval, position: Int) {
        tvInterval.text = interval.toString()
        tvInterval.tag = position
    }
}
class ExpandableExerciseAdapter(groups: MutableList<out ExerciseGroup>, private val exerciseViewModel: ExerciseViewModel) : ExpandableRecyclerViewAdapter<ExerciseGroupViewHolder, IntervalViewHolder>(groups) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ExerciseGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_exercise_group, parent, false)
        val groupViewHolder = ExerciseGroupViewHolder(view, exerciseViewModel)
        return groupViewHolder
    }
    override fun onBindGroupViewHolder(holder: ExerciseGroupViewHolder, flatPosition: Int, group: ExpandableGroup<in Interval>) {
        holder.setGroupTitle(group)
        holder.setExercisesIntervalListener(flatPosition)
    }
    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): IntervalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_interval_item, parent, false)
        //val empty = view.findViewById<TextView>(R.id.tvNoIntervals)
        //if (groups)

        return IntervalViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: IntervalViewHolder, flatPosition: Int, group: ExpandableGroup<in Interval>, childIndex: Int) {
        val interval = group.getItems() as MutableList<in Interval>

        //if (interval.size == 0) holder.tvNoIntervals

        holder.bind(interval[childIndex] as Interval, flatPosition)

        //holder.tvInterval.text = if (interval[childIndex] != null) interval[childIndex].toString() else "No Intervals"
    }

}