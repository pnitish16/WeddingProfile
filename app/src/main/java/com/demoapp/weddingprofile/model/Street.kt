package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Street(

	@ColumnInfo(name = "streetNumber")
	@field:SerializedName("number")
	val number: Int? = null,

	@ColumnInfo(name = "streetName")
	@field:SerializedName("name")
	val name: String? = null
) : Parcelable