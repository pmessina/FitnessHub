package com.fitness.fitnesshub

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

import java.io.Serializable

@Entity(
    indices = [Index("exerciseId")],
    foreignKeys = [ForeignKey(
        entity = Exercise::class,
        parentColumns = ["id"],
        childColumns = ["exerciseId"],
        onDelete = ForeignKey.CASCADE
    )]
)
@Parcelize
data class Interval(@PrimaryKey(autoGenerate = true) val id:Int, val exerciseId:Int, val reps: Int, val sets:Int, val weight:Int): Parcelable{
    override fun toString(): String {
        return "Id: " + id + "exerciseId: " + exerciseId + " reps: " + reps + "sets: " + sets + "weight: " + weight
    }
}

