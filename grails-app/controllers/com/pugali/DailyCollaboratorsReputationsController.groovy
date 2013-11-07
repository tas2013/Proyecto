package com.pugali

import grails.rest.RestfulController

class DailyCollaboratorsReputationsController extends RestfulController {

    static responseFormats = ['json']
    DailyCollaboratorsReputationsController() {
        super(DailyCollaboratorReputation)
    }
}
