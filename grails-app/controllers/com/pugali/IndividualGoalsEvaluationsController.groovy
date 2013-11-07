package com.pugali

import grails.rest.RestfulController

class IndividualGoalsEvaluationsController extends RestfulController {

    static responseFormats = ['json']
    IndividualGoalsEvaluationsController() {
        super(IndividualGoalEvaluation)
    }
}
