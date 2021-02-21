package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Name(
	@ColumnInfo(name="lastName")
	@field:SerializedName("last")
	val last: String? = null,

	@ColumnInfo(name="title")
	@field:SerializedName("title")
	val title: String? = null,

	@ColumnInfo(name = "firstName")
	@field:SerializedName("first")
	val first: String? = null
) : Parcelable