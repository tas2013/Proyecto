package com.pugali

import grails.rest.RestfulController

class PositionsController extends RestfulController {

    static responseFormats = ['json']
    PositionsController() {
        super(Position)
    }
}
