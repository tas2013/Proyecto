package com.pugali

class DailyCollaboratorReputation {

	Date date
	int reputation

	static belongsTo = [collaborator: Collaborator]

    static constraints = {
    	collaborator()
    	date nullable: false
    	reputation min: 0, max: 100
    }

    String toString() {
    	return String.valueOf(reputation) + " (" + date.format("dd/mm/yy") + ")"
    }
}
