package com.pugali

class BusinessGoalEvaluation {

	Date date
	int evaluation

	static belongsTo = [businessGoal: BusinessGoal]

    static constraints = {
    	businessGoal()
    	date nullable: false
    	evaluation min: 1, max: 100
    }

    String toString() {
    	return String.valueOf(evaluation) + " (" + date.format("dd/mm/yy") + ")"
    }
}
