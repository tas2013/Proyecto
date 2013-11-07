package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IndividualGoalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond IndividualGoal.list(params), model:[individualGoalInstanceCount: IndividualGoal.count()]
    }

    def show(IndividualGoal individualGoalInstance) {
        respond individualGoalInstance
    }

    def create() {
        respond new IndividualGoal(params)
    }

    @Transactional
    def save(IndividualGoal individualGoalInstance) {
        if (individualGoalInstance == null) {
            notFound()
            return
        }

        if (individualGoalInstance.hasErrors()) {
            respond individualGoalInstance.errors, view:'create'
            return
        }

        individualGoalInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'individualGoalInstance.label', default: 'IndividualGoal'), individualGoalInstance.id])
                redirect individualGoalInstance
            }
            '*' { respond individualGoalInstance, [status: CREATED] }
        }
    }

    def edit(IndividualGoal individualGoalInstance) {
        respond individualGoalInstance
    }

    @Transactional
    def update(IndividualGoal individualGoalInstance) {
        if (individualGoalInstance == null) {
            notFound()
            return
        }

        if (individualGoalInstance.hasErrors()) {
            respond individualGoalInstance.errors, view:'edit'
            return
        }

        individualGoalInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'IndividualGoal.label', default: 'IndividualGoal'), individualGoalInstance.id])
                redirect individualGoalInstance
            }
            '*'{ respond individualGoalInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(IndividualGoal individualGoalInstance) {

        if (individualGoalInstance == null) {
            notFound()
            return
        }

        individualGoalInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'IndividualGoal.label', default: 'IndividualGoal'), individualGoalInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'individualGoalInstance.label', default: 'IndividualGoal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
