package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FinalPeriodCollaboratorReputationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FinalPeriodCollaboratorReputation.list(params), model:[finalPeriodCollaboratorReputationInstanceCount: FinalPeriodCollaboratorReputation.count()]
    }

    def show(FinalPeriodCollaboratorReputation finalPeriodCollaboratorReputationInstance) {
        respond finalPeriodCollaboratorReputationInstance
    }

    def create() {
        respond new FinalPeriodCollaboratorReputation(params)
    }

    @Transactional
    def save(FinalPeriodCollaboratorReputation finalPeriodCollaboratorReputationInstance) {
        if (finalPeriodCollaboratorReputationInstance == null) {
            notFound()
            return
        }

        if (finalPeriodCollaboratorReputationInstance.hasErrors()) {
            respond finalPeriodCollaboratorReputationInstance.errors, view:'create'
            return
        }

        finalPeriodCollaboratorReputationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'finalPeriodCollaboratorReputationInstance.label', default: 'FinalPeriodCollaboratorReputation'), finalPeriodCollaboratorReputationInstance.id])
                redirect finalPeriodCollaboratorReputationInstance
            }
            '*' { respond finalPeriodCollaboratorReputationInstance, [status: CREATED] }
        }
    }

    def edit(FinalPeriodCollaboratorReputation finalPeriodCollaboratorReputationInstance) {
        respond finalPeriodCollaboratorReputationInstance
    }

    @Transactional
    def update(FinalPeriodCollaboratorReputation finalPeriodCollaboratorReputationInstance) {
        if (finalPeriodCollaboratorReputationInstance == null) {
            notFound()
            return
        }

        if (finalPeriodCollaboratorReputationInstance.hasErrors()) {
            respond finalPeriodCollaboratorReputationInstance.errors, view:'edit'
            return
        }

        finalPeriodCollaboratorReputationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FinalPeriodCollaboratorReputation.label', default: 'FinalPeriodCollaboratorReputation'), finalPeriodCollaboratorReputationInstance.id])
                redirect finalPeriodCollaboratorReputationInstance
            }
            '*'{ respond finalPeriodCollaboratorReputationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(FinalPeriodCollaboratorReputation finalPeriodCollaboratorReputationInstance) {

        if (finalPeriodCollaboratorReputationInstance == null) {
            notFound()
            return
        }

        finalPeriodCollaboratorReputationInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FinalPeriodCollaboratorReputation.label', default: 'FinalPeriodCollaboratorReputation'), finalPeriodCollaboratorReputationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'finalPeriodCollaboratorReputationInstance.label', default: 'FinalPeriodCollaboratorReputation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
