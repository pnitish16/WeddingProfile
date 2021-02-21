package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Picture(
	@ColumnInfo(name = "thumbnail")
	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@ColumnInfo(name = "large")
	@field:SerializedName("large")
	val large: String? = null,

	@ColumnInfo(name = "medium")
	@field:SerializedName("medium")
	val medium: String? = null
)