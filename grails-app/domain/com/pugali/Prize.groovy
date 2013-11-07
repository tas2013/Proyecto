package com.pugali

class Prize {

	String shortDescription
	String largeDescription
	int minimumEvaluation

	static belongsTo = [period: OrganizationPeriod]

	static hasMany = [candidates: Collaborator]

    static constraints = {
    	period()
    	shortDescription nullable: false, blank: false
    	largeDescription nullable: false, blank: false
    	minimumEvaluation min:1, max: 100
    	candidates()
    }

    String toString() {
    	return shortDescription + " (" + period.toString() + ")"
    } 
}
