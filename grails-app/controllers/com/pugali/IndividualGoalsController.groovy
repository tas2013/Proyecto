package com.pugali

import grails.rest.RestfulController

class IndividualGoalsController extends RestfulController {

    static responseFormats = ['json']
    IndividualGoalsController() {
        super(IndividualGoal)
    }
}
