package com.pugali

import grails.rest.RestfulController

class PrizesController extends RestfulController {

    static responseFormats = ['json']
    PrizesController() {
        super(Prize)
    }
}
