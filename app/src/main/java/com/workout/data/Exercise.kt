package com.workout.data

data class Exercise(override val duration: Long = 30000, override val caption: String = "") : Timable