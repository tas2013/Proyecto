package com.pugali



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OrganizationPeriodController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond OrganizationPeriod.list(params), model:[organizationPeriodInstanceCount: OrganizationPeriod.count()]
    }

    def show(OrganizationPeriod organizationPeriodInstance) {
        respond organizationPeriodInstance
    }

    def create() {
        respond new OrganizationPeriod(params)
    }

    @Transactional
    def save(OrganizationPeriod organizationPeriodInstance) {
        if (organizationPeriodInstance == null) {
            notFound()
            return
        }

        if (organizationPeriodInstance.hasErrors()) {
            respond organizationPeriodInstance.errors, view:'create'
            return
        }

        organizationPeriodInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'organizationPeriodInstance.label', default: 'OrganizationPeriod'), organizationPeriodInstance.id])
                redirect organizationPeriodInstance
            }
            '*' { respond organizationPeriodInstance, [status: CREATED] }
        }
    }

    def edit(OrganizationPeriod organizationPeriodInstance) {
        respond organizationPeriodInstance
    }

    @Transactional
    def update(OrganizationPeriod organizationPeriodInstance) {
        if (organizationPeriodInstance == null) {
            notFound()
            return
        }

        if (organizationPeriodInstance.hasErrors()) {
            respond organizationPeriodInstance.errors, view:'edit'
            return
        }

        organizationPeriodInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'OrganizationPeriod.label', default: 'OrganizationPeriod'), organizationPeriodInstance.id])
                redirect organizationPeriodInstance
            }
            '*'{ respond organizationPeriodInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(OrganizationPeriod organizationPeriodInstance) {

        if (organizationPeriodInstance == null) {
            notFound()
            return
        }

        organizationPeriodInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'OrganizationPeriod.label', default: 'OrganizationPeriod'), organizationPeriodInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizationPeriodInstance.label', default: 'OrganizationPeriod'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
