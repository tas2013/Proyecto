package com.pugali

class IndividualGoalEvaluation {

	Date date
	int attitudeEvaluation
	int efficiencyEvaluation

	static belongsTo = [individualGoal: IndividualGoal]

    static constraints = {
    	individualGoal()
    	date nullable: false
    	attitudeEvaluation min: 1, max: 100
    	efficiencyEvaluation min: 1, max: 100
    }

    String toString() {
    	return "Attitude: " + String.valueOf(attitudeEvaluation) + ", Efficiency: " + String.valueOf(efficiencyEvaluation) + " (" + date.format("dd/mm/yy") + ")"
    }
}
