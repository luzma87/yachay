package yachay.parametros

import yachay.parametros.TipoPersona

/**
 * Controlador
 */
class TipoPersonaController {

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
        def title = g.message(code: "tipopersona.list", default: "TipoPersona List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [tipoPersonaInstanceList: TipoPersona.list(params), tipoPersonaInstanceTotal: TipoPersona.count(), title: title, params: params]
    }

    /**
     * Acción
     */
    def form = {
        def title
        def tipoPersonaInstance

        if (params.source == "create") {
            tipoPersonaInstance = new TipoPersona()
            tipoPersonaInstance.properties = params
            title = g.message(code: "tipopersona.create", default: "Create TipoPersona")
        } else if (params.source == "edit") {
            tipoPersonaInstance = TipoPersona.get(params.id)
            if (!tipoPersonaInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "tipopersona.edit", default: "Edit TipoPersona")
        }

        return [tipoPersonaInstance: tipoPersonaInstance, title: title, source: params.source]
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
            title = g.message(code: "tipopersona.edit", default: "Edit TipoPersona")
            def tipoPersonaInstance = TipoPersona.get(params.id)
            if (tipoPersonaInstance) {
                tipoPersonaInstance.properties = params
                if (!tipoPersonaInstance.hasErrors() && tipoPersonaInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), tipoPersonaInstance.id])}"
                    redirect(action: "show", id: tipoPersonaInstance.id)
                }
                else {
                    render(view: "form", model: [tipoPersonaInstance: tipoPersonaInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "tipopersona.create", default: "Create TipoPersona")
            def tipoPersonaInstance = new TipoPersona(params)
            if (tipoPersonaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), tipoPersonaInstance.id])}"
                redirect(action: "show", id: tipoPersonaInstance.id)
            }
            else {
                render(view: "form", model: [tipoPersonaInstance: tipoPersonaInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción
     */
    def update = {
        def tipoPersonaInstance = TipoPersona.get(params.id)
        if (tipoPersonaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoPersonaInstance.version > version) {

                    tipoPersonaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoPersona.label', default: 'TipoPersona')] as Object[], "Another user has updated this TipoPersona while you were editing")
                    render(view: "edit", model: [tipoPersonaInstance: tipoPersonaInstance])
                    return
                }
            }
            tipoPersonaInstance.properties = params
            if (!tipoPersonaInstance.hasErrors() && tipoPersonaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), tipoPersonaInstance.id])}"
                redirect(action: "show", id: tipoPersonaInstance.id)
            }
            else {
                render(view: "edit", model: [tipoPersonaInstance: tipoPersonaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción
     */
    def show = {
        def tipoPersonaInstance = TipoPersona.get(params.id)
        if (!tipoPersonaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "tipopersona.show", default: "Show TipoPersona")

            [tipoPersonaInstance: tipoPersonaInstance, title: title]
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
        def tipoPersonaInstance = TipoPersona.get(params.id)
        if (tipoPersonaInstance) {
            try {
                tipoPersonaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoPersona.label', default: 'TipoPersona'), params.id])}"
            redirect(action: "list")
        }
    }
}
