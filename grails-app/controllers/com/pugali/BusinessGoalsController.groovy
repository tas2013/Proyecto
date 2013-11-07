package com.pugali

import grails.rest.RestfulController

class BusinessGoalsController extends RestfulController {

    static responseFormats = ['json']
    BusinessGoalsController() {
        super(BusinessGoal)
    }
}
