package com.pugali

class Area {

	String name

	static hasMany = [positions: Position]

    static constraints = {
    	name nullable: false, blank: false
    	positions()
    }

    String toString() {
    	return name
    }
}
