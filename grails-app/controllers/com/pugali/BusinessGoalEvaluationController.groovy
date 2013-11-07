package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BusinessGoalEvaluationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BusinessGoalEvaluation.list(params), model:[businessGoalEvaluationInstanceCount: BusinessGoalEvaluation.count()]
    }

    def show(BusinessGoalEvaluation businessGoalEvaluationInstance) {
        respond businessGoalEvaluationInstance
    }

    def create() {
        respond new BusinessGoalEvaluation(params)
    }

    @Transactional
    def save(BusinessGoalEvaluation businessGoalEvaluationInstance) {
        if (businessGoalEvaluationInstance == null) {
            notFound()
            return
        }

        if (businessGoalEvaluationInstance.hasErrors()) {
            respond businessGoalEvaluationInstance.errors, view:'create'
            return
        }

        businessGoalEvaluationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'businessGoalEvaluationInstance.label', default: 'BusinessGoalEvaluation'), businessGoalEvaluationInstance.id])
                redirect businessGoalEvaluationInstance
            }
            '*' { respond businessGoalEvaluationInstance, [status: CREATED] }
        }
    }

    def edit(BusinessGoalEvaluation businessGoalEvaluationInstance) {
        respond businessGoalEvaluationInstance
    }

    @Transactional
    def update(BusinessGoalEvaluation businessGoalEvaluationInstance) {
        if (businessGoalEvaluationInstance == null) {
            notFound()
            return
        }

        if (businessGoalEvaluationInstance.hasErrors()) {
            respond businessGoalEvaluationInstance.errors, view:'edit'
            return
        }

        businessGoalEvaluationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BusinessGoalEvaluation.label', default: 'BusinessGoalEvaluation'), businessGoalEvaluationInstance.id])
                redirect businessGoalEvaluationInstance
            }
            '*'{ respond businessGoalEvaluationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BusinessGoalEvaluation businessGoalEvaluationInstance) {

        if (businessGoalEvaluationInstance == null) {
            notFound()
            return
        }

        businessGoalEvaluationInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BusinessGoalEvaluation.label', default: 'BusinessGoalEvaluation'), businessGoalEvaluationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'businessGoalEvaluationInstance.label', default: 'BusinessGoalEvaluation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
