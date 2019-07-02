package com.fitness.fitnesshub

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_exercise.*

class AddExerciseActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)

//        val data = intent.extras
//
//        val tvName = data["tvName"]
//        val tvMethod = data["tvMethod"]
//        val tvMuscleGroup = data["tvMuscleGroup"]
//
//        val listener = this as OnUpdateExerciseListener
//
//        edtName.text = tvName as Editable
//        edtMethod.text = tvMethod as Editable
//        edtMuscleGroup.text = tvMuscleGroup as Editable
//
//        edtName.addTextChangedListener(this);

        btnEditSubmit.setOnClickListener{view ->

            val exercise = Exercise(null, edtName.text.toString(), edtMuscleGroup.text.toString(), edtMethod.text.toString())
//            val db = FirebaseFirestore.getInstance()
//
//            db.collection("exercises").add(exercise).addOnSuccessListener {
//                val intent = Intent(this, ExerciseHomeActivity::class.java)
//                startActivity(intent)
//            }

        }
    }

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(editable: Editable) {

    }
}

//interface OnUpdateExerciseListener {
//    fun updateExercise(exercise: Exercise)
//}