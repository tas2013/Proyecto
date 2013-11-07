package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DailyCollaboratorReputationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DailyCollaboratorReputation.list(params), model:[dailyCollaboratorReputationInstanceCount: DailyCollaboratorReputation.count()]
    }

    def show(DailyCollaboratorReputation dailyCollaboratorReputationInstance) {
        respond dailyCollaboratorReputationInstance
    }

    def create() {
        respond new DailyCollaboratorReputation(params)
    }

    @Transactional
    def save(DailyCollaboratorReputation dailyCollaboratorReputationInstance) {
        if (dailyCollaboratorReputationInstance == null) {
            notFound()
            return
        }

        if (dailyCollaboratorReputationInstance.hasErrors()) {
            respond dailyCollaboratorReputationInstance.errors, view:'create'
            return
        }

        dailyCollaboratorReputationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dailyCollaboratorReputationInstance.label', default: 'DailyCollaboratorReputation'), dailyCollaboratorReputationInstance.id])
                redirect dailyCollaboratorReputationInstance
            }
            '*' { respond dailyCollaboratorReputationInstance, [status: CREATED] }
        }
    }

    def edit(DailyCollaboratorReputation dailyCollaboratorReputationInstance) {
        respond dailyCollaboratorReputationInstance
    }

    @Transactional
    def update(DailyCollaboratorReputation dailyCollaboratorReputationInstance) {
        if (dailyCollaboratorReputationInstance == null) {
            notFound()
            return
        }

        if (dailyCollaboratorReputationInstance.hasErrors()) {
            respond dailyCollaboratorReputationInstance.errors, view:'edit'
            return
        }

        dailyCollaboratorReputationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DailyCollaboratorReputation.label', default: 'DailyCollaboratorReputation'), dailyCollaboratorReputationInstance.id])
                redirect dailyCollaboratorReputationInstance
            }
            '*'{ respond dailyCollaboratorReputationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DailyCollaboratorReputation dailyCollaboratorReputationInstance) {

        if (dailyCollaboratorReputationInstance == null) {
            notFound()
            return
        }

        dailyCollaboratorReputationInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DailyCollaboratorReputation.label', default: 'DailyCollaboratorReputation'), dailyCollaboratorReputationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dailyCollaboratorReputationInstance.label', default: 'DailyCollaboratorReputation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
