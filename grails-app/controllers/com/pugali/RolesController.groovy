package com.pugali

import grails.rest.RestfulController

class RolesController extends RestfulController {

    static responseFormats = ['json']
    RolesController() {
        super(Role)
    }
}
