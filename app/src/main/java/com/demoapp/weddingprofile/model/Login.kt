package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Login(
	@ColumnInfo(name = "sha1")
	@field:SerializedName("sha1")
	val sha1: String? = null,

	@ColumnInfo(name = "password")
	@field:SerializedName("password")
	val password: String? = null,

	@ColumnInfo(name = "salt")
	@field:SerializedName("salt")
	val salt: String? = null,

	@ColumnInfo(name = "sha256")
	@field:SerializedName("sha256")
	val sha256: String? = null,

	@ColumnInfo(name = "uuid")
	@field:SerializedName("uuid")
	val uuid: String? = null,

	@ColumnInfo(name = "username")
	@field:SerializedName("username")
	val username: String? = null,

	@ColumnInfo(name = "md5")
	@field:SerializedName("md5")
	val md5: String? = null
) : Parcelable