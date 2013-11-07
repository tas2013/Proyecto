package com.pugali

import grails.rest.RestfulController

class FinalPeriodCollaboratorsReputationsController extends RestfulController {

    static responseFormats = ['json']
    FinalPeriodCollaboratorsReputationsController() {
        super(FinalPeriodCollaboratorReputation)
    }
}
