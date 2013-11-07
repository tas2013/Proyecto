package com.pugali

class Collaborator {

	String firstName
	String lastName
	Date dateOfEntry
	String sex
	Date dateOfBirth
	String state
	User user

	static belongsTo = [organization: Organization]

	static hasMany = [dailyReputations: DailyCollaboratorReputation, individualGoals: IndividualGoal, finalReputations: FinalPeriodCollaboratorReputation]

    static constraints = {
    	organization()
    	firstName nullable: false, blank: false
    	lastName nullable: false, blank: false
    	dateOfEntry nullable: false
    	sex inList: ['Male', 'Female'], blank: false, nullable: false
    	dateOfBirth nullable: false
    	state inList: ['Enabled', 'Unabled'], nullable: false, blank: false
    	user nullable: true
    }

    String toString() {
    	return firstName + " " + lastName
    }
}
