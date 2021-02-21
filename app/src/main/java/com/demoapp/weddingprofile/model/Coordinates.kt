package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(

	@ColumnInfo(name = "latitude")
	@field:SerializedName("latitude")
	val latitude: String? = null,

	@ColumnInfo(name = "longitude")
	@field:SerializedName("longitude")
	val longitude: String? = null
) : Parcelable