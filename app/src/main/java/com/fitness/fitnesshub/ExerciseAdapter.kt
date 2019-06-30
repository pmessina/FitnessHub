package com.fitness.fitnesshub

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.multiselector.MultiSelector
import com.bignerdranch.android.multiselector.SwappingHolder

class ExerciseHolder(val v: View, val selector: MultiSelector, private val exerciseViewModel: ExerciseViewModel)
    : SwappingHolder(v, selector) {

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

            intent.putExtra("exercise", exerciseViewModel.exerciseList[position])

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
        val holder =  ExerciseHolder(inflater, selector, exerciseViewModel)
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.content_exercise_item, parent, false)

        val holder =  ExerciseHolder(inflater, exerciseViewModel)
        holder.setExercisesIntervalListener()
        return holder
    }

}


//    fun setExercise(exercise: Exercise) {
//        //exerciseList.add(exercise)
//        exerciseAdapter.notifyDataSetChanged()
//    }


//    fun updateExercise(id: Int) {
//        exerciseAdapter.notifyItemChanged(id)
//    }
//
//    fun removeExercise(id: Int) {
//        exerciseList[id]
//        exerciseAdapter.notifyDataSetChanged()
//    }

//    fun getExerciseAdapter(): ExerciseAdapter {
//        return exerciseAdapter
//    }

//    fun setUpExerciseAdapter(exercises: List<Exercise>) {
//
//        //exerciseAdapter.setExerciseList(exercises)
//        //        recyclerView.setAdapter(exerciseAdapter);
//    }


//class ExerciseHolder(v: View) : SwappingHolder(v, selector), View.OnLongClickListener {
//    private var textView = v as TextView
//    companion object{
//        private val selector = MultiSelector()
//    }
//
//    init {
//        v.isLongClickable = true
//    }
//
//    fun bind(documentSnapshot: DocumentSnapshot, listener: OnExerciseSelectedListener){
//        val exercise = documentSnapshot.toObject(Exercise::class.java)
//
//        //textView.text = if (exercise?.name.isNullOrEmpty()) "No Name" else exercise?.name
//
//        itemView.setOnClickListener {
//            Log.d(TAG, "Exercise Selected")
//            listener.onExerciseSelected(documentSnapshot, this, selector)
//        }
//
//
//    }
//
//    override fun onLongClick(v: View?): Boolean {
//
//        if (!selector.isSelectable){
//            selector.isSelectable = true
//            selector.setSelected(this, true)
//            return true
//        }
//        return false
//    }
//
//}


//class ExerciseAdapter(val exercises: ArrayList<String>, val query: Query, val listener:OnExerciseSelectedListener) : RecyclerView.Adapter<ExerciseHolder>(),
//    EventListener<QuerySnapshot> {
//
//    private val mSnapshots = ArrayList<DocumentSnapshot>()
//    var mQuery: Query? = null
//    var mRegistration: ListenerRegistration? = null
//
//    init{
//        setQuery(query)
//    }
//
//    override fun onEvent(documentSnapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {
//            if (e != null) return
//
//        for (change in documentSnapshots!!.documentChanges){
//            val snapshot = change.document
//
//            when(change.type){
//                DocumentChange.Type.ADDED ->{ onDocumentAdded(change) }
//                DocumentChange.Type.MODIFIED ->{ onDocumentModified(change)}
//                DocumentChange.Type.REMOVED ->{ onDocumentRemoved(change)}
//            }
//
//        }
//    }
//
//    private fun onDocumentAdded(change:DocumentChange){
//        mSnapshots.add(change.newIndex, change.document)
//        notifyItemInserted(change.newIndex)
//    }
//
//    private fun onDocumentModified(change:DocumentChange){
//        if (change.oldIndex == change.newIndex){
//            mSnapshots.set(change.oldIndex, change.document)
//            notifyItemChanged(change.oldIndex)
//        }
//        else{
//            mSnapshots.removeAt(change.oldIndex)
//            mSnapshots.add(change.newIndex, change.document)
//            notifyItemMoved(change.oldIndex, change.newIndex)
//        }
//
//    }
//
//    private fun onDocumentRemoved(change:DocumentChange){
//        mSnapshots.removeAt(change.oldIndex)
//        notifyItemRemoved(change.oldIndex)
//    }
//
//    override fun getItemCount(): Int {
//        return mSnapshots.size
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
//        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.content_exercise_item, parent, false)
//
//        return ExerciseHolder(inflater)
//    }
//
//    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
//        //holder.textView.text = exercises[position]
//
//        val docSnapshot = getSnapshot(position)//.toObject(Exercise::class.java)
//
//        holder.bind(docSnapshot, listener)
//    }
//
//    private fun startListening() {
//        if (mQuery != null && mRegistration == null) {
//            mRegistration = query.addSnapshotListener(this)
//        }
//    }
//
//    private fun stopListening() {
//        mRegistration?.remove()
//
//        mSnapshots.clear()
//        notifyDataSetChanged()
//    }
//
//    fun setQuery(query: Query) {
//        stopListening()
//        mQuery = query
//        startListening()
//    }
//
//    private fun getSnapshot(index: Int): DocumentSnapshot {
//        return mSnapshots[index]
//    }
//
//    private fun onError(e: FirebaseFirestoreException) {};
//
//    private fun onDataChanged() {}
//
//}

