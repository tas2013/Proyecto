package com.pugali

class OrganizationPeriod {

	Date initialDate
	Date finalDate

	static belongsTo = [organization: Organization]

	static hasMany = [businessGoals: BusinessGoal, prizes: Prize]

    static constraints = {
    	organization()
    	initialDate nullable: false
    	finalDate nullable: false
    	businessGoals()
    	prizes()
    }

    String toString() {
    	return initialDate.format('dd/mm/yy') + " -> " + finalDate.format('dd/mm/yy')
    }
}
