package com.fitness.fitnesshub

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String = "",
    val muscleGroup: String ="",
    val method: String = ""): Serializable{


}

