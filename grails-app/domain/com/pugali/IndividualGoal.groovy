package com.pugali

class IndividualGoal {

	BusinessGoal fromBusinessGoal
	IndividualGoal fromIndividualGoal
	String shortDescription
	String largeDescription
	int importanceWeight
	Date dateOfCreation
	String state

    static belongsTo = [collaborator: Collaborator]

    static hasMany = [evaluations: IndividualGoalEvaluation]

    static constraints = {
    	collaborator()
    	fromBusinessGoal nullable: true
    	fromIndividualGoal nullable: true
    	shortDescription nullable: false, blank: false
    	largeDescription nullable: false, blank: false
    	importanceWeight min: 1, max: 100
    	dateOfCreation nullable: false
    	state inList: ['Enabled', 'Unabled'], blank: false, nullable: false
    	evaluations()
    }

    String toString() {
    	return shortDescription + "(Weight: " + String.valueOf(importanceWeight) + ")"
    }
}
