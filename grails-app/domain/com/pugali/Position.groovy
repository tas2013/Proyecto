package com.pugali

class Position {

	String name
	Collaborator collaborator
	Date initialDate

	static belongsTo = [area: Area, parent: Position]

	static hasMany = [subPositions: Position]

    static constraints = {
    	area()
    	parent nullable: true
    	name nullable: false, blank: false
    	collaborator nullable: false
    	initialDate nullable: false
    	subPositions()
    }

    String toString() {
    	return name + " (" + collaborator.toString() + ")"
    }
}
