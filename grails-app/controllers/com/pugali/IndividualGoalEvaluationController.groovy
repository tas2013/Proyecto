package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IndividualGoalEvaluationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond IndividualGoalEvaluation.list(params), model:[individualGoalEvaluationInstanceCount: IndividualGoalEvaluation.count()]
    }

    def show(IndividualGoalEvaluation individualGoalEvaluationInstance) {
        respond individualGoalEvaluationInstance
    }

    def create() {
        respond new IndividualGoalEvaluation(params)
    }

    @Transactional
    def save(IndividualGoalEvaluation individualGoalEvaluationInstance) {
        if (individualGoalEvaluationInstance == null) {
            notFound()
            return
        }

        if (individualGoalEvaluationInstance.hasErrors()) {
            respond individualGoalEvaluationInstance.errors, view:'create'
            return
        }

        individualGoalEvaluationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'individualGoalEvaluationInstance.label', default: 'IndividualGoalEvaluation'), individualGoalEvaluationInstance.id])
                redirect individualGoalEvaluationInstance
            }
            '*' { respond individualGoalEvaluationInstance, [status: CREATED] }
        }
    }

    def edit(IndividualGoalEvaluation individualGoalEvaluationInstance) {
        respond individualGoalEvaluationInstance
    }

    @Transactional
    def update(IndividualGoalEvaluation individualGoalEvaluationInstance) {
        if (individualGoalEvaluationInstance == null) {
            notFound()
            return
        }

        if (individualGoalEvaluationInstance.hasErrors()) {
            respond individualGoalEvaluationInstance.errors, view:'edit'
            return
        }

        individualGoalEvaluationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'IndividualGoalEvaluation.label', default: 'IndividualGoalEvaluation'), individualGoalEvaluationInstance.id])
                redirect individualGoalEvaluationInstance
            }
            '*'{ respond individualGoalEvaluationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(IndividualGoalEvaluation individualGoalEvaluationInstance) {

        if (individualGoalEvaluationInstance == null) {
            notFound()
            return
        }

        individualGoalEvaluationInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'IndividualGoalEvaluation.label', default: 'IndividualGoalEvaluation'), individualGoalEvaluationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'individualGoalEvaluationInstance.label', default: 'IndividualGoalEvaluation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
