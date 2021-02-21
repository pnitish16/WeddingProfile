package com.demoapp.weddingprofile.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("info")
	val info: Info? = null
)