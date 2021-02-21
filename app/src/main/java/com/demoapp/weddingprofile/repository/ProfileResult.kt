package com.demoapp.weddingprofile.repository

import com.demoapp.weddingprofile.model.ResultsItem

sealed class ProfileResult {
  data class Content(val profiles: List<ResultsItem?>) : ProfileResult()
  data class Error(val throwable: Throwable) : ProfileResult()
}
