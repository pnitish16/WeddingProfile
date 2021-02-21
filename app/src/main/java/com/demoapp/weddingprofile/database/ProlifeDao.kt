package com.demoapp.weddingprofile.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demoapp.weddingprofile.model.ResultsItem

@Dao
abstract class ProfileDatabaseDao{

    @Query("SELECT * FROM profile")
    abstract fun getAllProfiles(): List<ResultsItem?>

    @Insert
    abstract fun insertProfile(resultsItem: ResultsItem?)

    @Update
    abstract fun updateProfile(resultsItem: ResultsItem?)
}