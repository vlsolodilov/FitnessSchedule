package com.solodilov.fitnessschedule.data.model


import com.google.gson.annotations.SerializedName

data class OptionDto(
    @SerializedName("club_name")
    val clubName: String,
    @SerializedName("link_android")
    val linkAndroid: String,
    @SerializedName("link_ios")
    val linkIos: String,
    @SerializedName("primary_color")
    val primaryColor: String,
    @SerializedName("secondary_color")
    val secondaryColor: String
)