package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BusinessGoalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BusinessGoal.list(params), model:[businessGoalInstanceCount: BusinessGoal.count()]
    }

    def show(BusinessGoal businessGoalInstance) {
        respond businessGoalInstance
    }

    def create() {
        respond new BusinessGoal(params)
    }

    @Transactional
    def save(BusinessGoal businessGoalInstance) {
        if (businessGoalInstance == null) {
            notFound()
            return
        }

        if (businessGoalInstance.hasErrors()) {
            respond businessGoalInstance.errors, view:'create'
            return
        }

        businessGoalInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'businessGoalInstance.label', default: 'BusinessGoal'), businessGoalInstance.id])
                redirect businessGoalInstance
            }
            '*' { respond businessGoalInstance, [status: CREATED] }
        }
    }

    def edit(BusinessGoal businessGoalInstance) {
        respond businessGoalInstance
    }

    @Transactional
    def update(BusinessGoal businessGoalInstance) {
        if (businessGoalInstance == null) {
            notFound()
            return
        }

        if (businessGoalInstance.hasErrors()) {
            respond businessGoalInstance.errors, view:'edit'
            return
        }

        businessGoalInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BusinessGoal.label', default: 'BusinessGoal'), businessGoalInstance.id])
                redirect businessGoalInstance
            }
            '*'{ respond businessGoalInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BusinessGoal businessGoalInstance) {

        if (businessGoalInstance == null) {
            notFound()
            return
        }

        businessGoalInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BusinessGoal.label', default: 'BusinessGoal'), businessGoalInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'businessGoalInstance.label', default: 'BusinessGoal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
