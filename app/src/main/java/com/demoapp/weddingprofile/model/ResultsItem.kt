package com.demoapp.weddingprofile.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "profile", indices = [Index(
        value = ["profileId", "nat", "gender", "phone", "name", "cell", "email"], unique = true
    )]
)
data class ResultsItem(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "profileId")
    val profileId: Int,

    @ColumnInfo
    @field:SerializedName("nat")
    val nat: String? = null,

    @ColumnInfo
    @field:SerializedName("gender")
    val gender: String? = null,

    @ColumnInfo
    @field:SerializedName("phone")
    val phone: String? = null,

    @Embedded
    @field:SerializedName("dob")
    val dob: Dob? = null,

    @Embedded
    @field:SerializedName("name")
    val name: Name? = null,

    @Embedded
    @field:SerializedName("registered")
    val registered: Registered? = null,

    @Embedded
    @field:SerializedName("location")
    val location: Location? = null,

    @Embedded
    @field:SerializedName("id")
    val id: Id? = null,

    @Embedded
    @field:SerializedName("login")
    val login: Login? = null,

    @ColumnInfo
    @field:SerializedName("cell")
    val cell: String? = null,

    @ColumnInfo
    @field:SerializedName("email")
    val email: String? = null,

    @Embedded
    @field:SerializedName("picture")
    val picture: Picture? = null,

    @ColumnInfo(name = "responseSaved")
    var responseSaved: Boolean? = false,

    @ColumnInfo(name = "isLiked")
    var isLiked: Boolean? = false
)