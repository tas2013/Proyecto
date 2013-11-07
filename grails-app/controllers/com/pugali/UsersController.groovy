package com.pugali

import grails.rest.RestfulController

class UsersController extends RestfulController {

    static responseFormats = ['json']
    UsersController() {
        super(User)
    }
}
