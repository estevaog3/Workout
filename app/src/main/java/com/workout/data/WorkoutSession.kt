package com.workout.data

data class WorkoutSession(val exerciseNames: List<String> = listOf("Jumping jacks", "Wall-sit", "Push-up", "Abdominal crunch", "Step-up onto chair", "Squat", "Triceps dip on chair",
        "Plank", "Running in place", "Lunge", "Push-up + rotate", "Side plank 1", "Side plank 2")) {

    val exercises = mutableListOf<Exercise>()
    val breaks = mutableListOf<Break>()

    init {
        exerciseNames.forEachIndexed {i, name ->
            exercises.add(Exercise(caption = name))
            if(i == 0){
                breaks.add(Break(caption = "Get ready for $name"))
            }else{
                breaks.add(Break(caption = "Next: $name"))
            }
        }
    }
}