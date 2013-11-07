package com.pugali

class BusinessGoal {

	String shortDescription
	String largeDescription

	static belongsTo = [period: OrganizationPeriod]

	static hasMany = [individualGoals: IndividualGoal, evaluations: BusinessGoalEvaluation]

    static constraints = {
    	shortDescription nullable: false, blank: false
    	largeDescription nullable: false, blank: false
    	period()
    	individualGoals()
    	evaluations()
    }

    String toString() {
    	return shortDescription
    }
}
