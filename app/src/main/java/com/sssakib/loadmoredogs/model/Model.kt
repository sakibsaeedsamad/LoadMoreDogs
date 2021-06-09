package com.sssakib.loadmoredogs.model

import com.google.gson.annotations.SerializedName

data class DogBreed(

    @SerializedName("name")
    val name: String?
)