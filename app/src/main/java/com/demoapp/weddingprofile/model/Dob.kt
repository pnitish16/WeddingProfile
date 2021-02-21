package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dob(

	@ColumnInfo(name = "date")
	@field:SerializedName("date")
	val date: String? = null,

	@ColumnInfo(name = "dobAge")
	@field:SerializedName("age")
	val age: Int? = null
) : Parcelable