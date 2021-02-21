package com.demoapp.weddingprofile.repository

import com.demoapp.weddingprofile.model.ResultsItem

sealed class MainViewState {
  object Loading : MainViewState()
  data class Content(val profiles: List<ResultsItem?>) : MainViewState()
  data class Error(val throwable: Throwable) : MainViewState()
}
