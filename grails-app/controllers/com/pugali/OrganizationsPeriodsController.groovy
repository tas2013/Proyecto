package com.pugali

import grails.rest.RestfulController

class OrganizationsPeriodsController extends RestfulController {

    static responseFormats = ['json']
    OrganizationsPeriodsController() {
        super(OrganizationPeriod)
    }
}
