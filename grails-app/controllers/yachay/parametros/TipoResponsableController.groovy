package yachay.parametros

import yachay.parametros.TipoResponsable

/**
 * Controlador
 */
class TipoResponsableController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción
     */
    def list = {
        def title = g.message(code: "tiporesponsable.list", default: "TipoResponsable List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [tipoResponsableInstanceList: TipoResponsable.list(params), tipoResponsableInstanceTotal: TipoResponsable.count(), title: title, params: params]
    }

    /**
     * Acción
     */
    def form = {
        def title
        def tipoResponsableInstance

        if (params.source == "create") {
            tipoResponsableInstance = new TipoResponsable()
            tipoResponsableInstance.properties = params
            title = g.message(code: "tiporesponsable.create", default: "Create TipoResponsable")
        } else if (params.source == "edit") {
            tipoResponsableInstance = TipoResponsable.get(params.id)
            if (!tipoResponsableInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "tiporesponsable.edit", default: "Edit TipoResponsable")
        }

        return [tipoResponsableInstance: tipoResponsableInstance, title: title, source: params.source]
    }

    /**
     * Acción
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "tiporesponsable.edit", default: "Edit TipoResponsable")
            def tipoResponsableInstance = TipoResponsable.get(params.id)
            if (tipoResponsableInstance) {
                tipoResponsableInstance.properties = params
                if (!tipoResponsableInstance.hasErrors() && tipoResponsableInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), tipoResponsableInstance.id])}"
                    redirect(action: "show", id: tipoResponsableInstance.id)
                }
                else {
                    render(view: "form", model: [tipoResponsableInstance: tipoResponsableInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "tiporesponsable.create", default: "Create TipoResponsable")
            def tipoResponsableInstance = new TipoResponsable(params)
            if (tipoResponsableInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), tipoResponsableInstance.id])}"
                redirect(action: "show", id: tipoResponsableInstance.id)
            }
            else {
                render(view: "form", model: [tipoResponsableInstance: tipoResponsableInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción
     */
    def update = {
        def tipoResponsableInstance = TipoResponsable.get(params.id)
        if (tipoResponsableInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoResponsableInstance.version > version) {

                    tipoResponsableInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoResponsable.label', default: 'TipoResponsable')] as Object[], "Another user has updated this TipoResponsable while you were editing")
                    render(view: "edit", model: [tipoResponsableInstance: tipoResponsableInstance])
                    return
                }
            }
            tipoResponsableInstance.properties = params
            if (!tipoResponsableInstance.hasErrors() && tipoResponsableInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), tipoResponsableInstance.id])}"
                redirect(action: "show", id: tipoResponsableInstance.id)
            }
            else {
                render(view: "edit", model: [tipoResponsableInstance: tipoResponsableInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción
     */
    def show = {
        def tipoResponsableInstance = TipoResponsable.get(params.id)
        if (!tipoResponsableInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "tiporesponsable.show", default: "Show TipoResponsable")

            [tipoResponsableInstance: tipoResponsableInstance, title: title]
        }
    }

    /**
     * Acción
     */
    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    /**
     * Acción
     */
    def delete = {
        def tipoResponsableInstance = TipoResponsable.get(params.id)
        if (tipoResponsableInstance) {
            try {
                tipoResponsableInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoResponsable.label', default: 'TipoResponsable'), params.id])}"
            redirect(action: "list")
        }
    }
}
