package com.pugali 

import grails.rest.RestfulController

class AreasController extends RestfulController {

    static responseFormats = ['json']
    AreasController() {
        super(Area)
    }
}
