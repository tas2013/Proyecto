package com.pugali

class Organization {

	String shortName
	String completeName
	Date dateOfCreation
	Collaborator director

	static hasMany = [collaborators: Collaborator, periods: OrganizationPeriod]

    static constraints = {
    	shortName nullable: false, blank: false
    	completeName nullable: false, blank: false
    	dateOfCreation nullable: false
    	director nullable: true
    	collaborators()
    	periods()
    }

    String toString() {
    	return shortName
    }
}
