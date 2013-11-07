package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PrizeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Prize.list(params), model:[prizeInstanceCount: Prize.count()]
    }

    def show(Prize prizeInstance) {
        respond prizeInstance
    }

    def create() {
        respond new Prize(params)
    }

    @Transactional
    def save(Prize prizeInstance) {
        if (prizeInstance == null) {
            notFound()
            return
        }

        if (prizeInstance.hasErrors()) {
            respond prizeInstance.errors, view:'create'
            return
        }

        prizeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'prizeInstance.label', default: 'Prize'), prizeInstance.id])
                redirect prizeInstance
            }
            '*' { respond prizeInstance, [status: CREATED] }
        }
    }

    def edit(Prize prizeInstance) {
        respond prizeInstance
    }

    @Transactional
    def update(Prize prizeInstance) {
        if (prizeInstance == null) {
            notFound()
            return
        }

        if (prizeInstance.hasErrors()) {
            respond prizeInstance.errors, view:'edit'
            return
        }

        prizeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Prize.label', default: 'Prize'), prizeInstance.id])
                redirect prizeInstance
            }
            '*'{ respond prizeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Prize prizeInstance) {

        if (prizeInstance == null) {
            notFound()
            return
        }

        prizeInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Prize.label', default: 'Prize'), prizeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'prizeInstance.label', default: 'Prize'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
