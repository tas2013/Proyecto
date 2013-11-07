package com.pugali

import grails.rest.RestfulController

class CollaboratorsController extends RestfulController {

    static responseFormats = ['json']
    CollaboratorsController() {
        super(Collaborator)
    }
}
