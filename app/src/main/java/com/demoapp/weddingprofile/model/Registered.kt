package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Registered(

	@ColumnInfo(name = "registeredDate")
	@field:SerializedName("date")
	val date: String? = null,

	@ColumnInfo(name = "registeredAge")
	@field:SerializedName("age")
	val age: Int? = null
) : Parcelable