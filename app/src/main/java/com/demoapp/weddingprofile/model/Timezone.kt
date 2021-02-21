package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Timezone(

	@ColumnInfo(name = "timezoneOffset")
	@field:SerializedName("offset")
	val offset: String? = null,

	@ColumnInfo(name = "timezoneDescription")
	@field:SerializedName("description")
	val description: String? = null
): Parcelable