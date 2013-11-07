package com.pugali

class FinalPeriodCollaboratorReputation {

	int finalReputation

	static belongsTo = [collaborator: Collaborator, period: OrganizationPeriod]

    static constraints = {
    	collaborator()
    	period()
    	finalReputation min: 1, max: 100
    }

    String toString() {
    	return period.toString() + " (" + String.valueOf(finalReputation) +")"
    }
}
