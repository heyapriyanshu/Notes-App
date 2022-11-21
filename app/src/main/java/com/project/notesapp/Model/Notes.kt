package com.project.notesapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "Notes")
@Parcelize
class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var title:String,
    var notes:String,
    var date:String,
    var prority: String
):Parcelable