package com.pugali

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class OrganizationsController {

    static allowedMethods = [save: "POST", index: "GET"]

    def index() {
        render Organization.list() as JSON
    }

    @Transactional
    def save(Organization organizationInstance) {
    	organizationInstance.dateOfCreation = new Date()
    	System.out.println organizationInstance.shortName + "; " + organizationInstance.completeName + "; " + organizationInstance.dateOfCreation
    	
    	if (organizationInstance == null) {
            render "NULO" as JSON
        }

        if (organizationInstance.hasErrors()) {
            render organizationInstance.errors as JSON
        }
        System.out.println "Va a guardar"
        organizationInstance.save flush:true
        if (organizationInstance.hasErrors()) {
            render organizationInstance.errors as JSON
        }
        System.out.println "Guardó"
        render organizationInstance as JSON
    }
}
