package com.example.a04_module_c

import java.util.*

data class Tour(
    val activityId: Int,
    val activityName: String,
    val activityDate: Date,
    val activityType: String,
    val activityDescription: String,
    val maxParticipant: Int,
    val joinedParticipant: Int,
    val presentNo: Int,
    val absentNo: Int,
    val isActive: Boolean
){

}