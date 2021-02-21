package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
	@ColumnInfo(name = "country")
	@field:SerializedName("country")
	val country: String? = null,

	@ColumnInfo(name = "city")
	@field:SerializedName("city")
	val city: String? = null,

	@Embedded
	@field:SerializedName("street")
	val street: Street? = null,

	@Embedded
	@field:SerializedName("timezone")
	val timezone: Timezone? = null,

	@ColumnInfo(name = "postcode")
	@field:SerializedName("postcode")
	val postcode: String? = null,

	@Embedded
	@field:SerializedName("coordinates")
	val coordinates: Coordinates? = null,

	@ColumnInfo(name = "state")
	@field:SerializedName("state")
	val state: String? = null
) : Parcelable