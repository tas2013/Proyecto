package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CollaboratorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Collaborator.list(params), model:[collaboratorInstanceCount: Collaborator.count()]
    }

    def show(Collaborator collaboratorInstance) {
        respond collaboratorInstance
    }

    def create() {
        respond new Collaborator(params)
    }

    @Transactional
    def save(Collaborator collaboratorInstance) {
        if (collaboratorInstance == null) {
            notFound()
            return
        }

        if (collaboratorInstance.hasErrors()) {
            respond collaboratorInstance.errors, view:'create'
            return
        }

        collaboratorInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'collaboratorInstance.label', default: 'Collaborator'), collaboratorInstance.id])
                redirect collaboratorInstance
            }
            '*' { respond collaboratorInstance, [status: CREATED] }
        }
    }

    def edit(Collaborator collaboratorInstance) {
        respond collaboratorInstance
    }

    @Transactional
    def update(Collaborator collaboratorInstance) {
        if (collaboratorInstance == null) {
            notFound()
            return
        }

        if (collaboratorInstance.hasErrors()) {
            respond collaboratorInstance.errors, view:'edit'
            return
        }

        collaboratorInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Collaborator.label', default: 'Collaborator'), collaboratorInstance.id])
                redirect collaboratorInstance
            }
            '*'{ respond collaboratorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Collaborator collaboratorInstance) {

        if (collaboratorInstance == null) {
            notFound()
            return
        }

        collaboratorInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Collaborator.label', default: 'Collaborator'), collaboratorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'collaboratorInstance.label', default: 'Collaborator'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
