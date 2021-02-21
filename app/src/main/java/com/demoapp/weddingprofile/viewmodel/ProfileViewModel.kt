package com.demoapp.weddingprofile.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.demoapp.weddingprofile.database.ProfileDatabase
import com.demoapp.weddingprofile.database.ProfileDatabaseDao
import com.demoapp.weddingprofile.model.ResultsItem
import com.demoapp.weddingprofile.repository.MainViewState
import com.demoapp.weddingprofile.repository.ProfileRepository
import com.demoapp.weddingprofile.repository.ProfileResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) :
    AndroidViewModel(application) {

    var profileListLiveData = MutableLiveData<List<ResultsItem?>>()
    var profileOfflineList = MutableLiveData<List<ResultsItem?>>()
    var toastMessage = MutableLiveData<String>()
    var profileListError = MutableLiveData<String>()
    private val _results = MutableLiveData<MainViewState>()
    val results: LiveData<MainViewState> = _results
    var profileDatabase: ProfileDatabase? = null
    var profileDatabaseDao: ProfileDatabaseDao? = null

    init {
        viewModelScope.launch {
            profileDatabase = ProfileDatabase.getInstance(application, this)
            profileDatabaseDao = profileDatabase?.profileDatabaseDao
        }
    }

    fun getOfflineItems() {
        CoroutineScope(Dispatchers.IO).launch {
            profileOfflineList.postValue(profileDatabase?.profileDatabaseDao?.getAllProfiles()!!)
        }
    }

    @SuppressLint("CheckResult")
    fun getProfiles() {
        _results.value =
            MainViewState.Loading

        viewModelScope.launch {

            val result =
                ProfileRepository.getProfiles(
                    10
                )

            _results.value = when (result) {
                is ProfileResult.Content -> {
                    for (resultItem in result.profiles) {
                        CoroutineScope(Dispatchers.IO).launch {
                            profileDatabase?.profileDatabaseDao?.insertProfile(resultItem)
                        }
                    }
                    val profiles = result.profiles
                    MainViewState.Content(
                        profiles
                    )
                }
                is ProfileResult.Error -> {
                    MainViewState.Error(
                        result.throwable
                    )
                }

            }
        }
    }

    fun likePerson(isLiked: Boolean, resultsItem: ResultsItem) {
        CoroutineScope(Dispatchers.IO).launch {
            resultsItem.isLiked = isLiked
            resultsItem.responseSaved = true
            profileDatabase?.profileDatabaseDao?.updateProfile(resultsItem)
            profileOfflineList.postValue(profileDatabase?.profileDatabaseDao?.getAllProfiles()!!)
        }
    }

    fun toastLike(isLiked: Boolean) {
        if (isLiked)
            toastMessage.value = "profile is accepted"
        else
            toastMessage.value = "profile is declined"
    }

    fun acceptClicked(resultsItem: ResultsItem) {
        likePerson(true, resultsItem)
        toastLike(true)
        getOfflineItems()
    }

    fun declineClicked(resultsItem: ResultsItem) {
        likePerson(false, resultsItem)
        toastLike(false)
        getOfflineItems()
    }


}