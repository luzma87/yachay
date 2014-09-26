package yachay.poa

import yachay.poa.Componente

/**
 * Controlador
 */
class ComponenteController {

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
        def title = g.message(code: "componente.list", default: "Componente List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [componenteInstanceList: Componente.list(params), componenteInstanceTotal: Componente.count(), title: title, params: params]
    }

    /**
     * Acción
     */
    def form = {
        def title
        def componenteInstance

        if (params.source == "create") {
            componenteInstance = new Componente()
            componenteInstance.properties = params
            title = g.message(code: "componente.create", default: "Create Componente")
        } else if (params.source == "edit") {
            componenteInstance = Componente.get(params.id)
            if (!componenteInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'componente.label', default: 'Componente'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "componente.edit", default: "Edit Componente")
        }

        return [componenteInstance: componenteInstance, title: title, source: params.source]
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
            title = g.message(code: "componente.edit", default: "Edit Componente")
            def componenteInstance = Componente.get(params.id)
            if (componenteInstance) {
                componenteInstance.properties = params
                if (!componenteInstance.hasErrors() && componenteInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'componente.label', default: 'Componente'), componenteInstance.id])}"
                    redirect(action: "show", id: componenteInstance.id)
                }
                else {
                    render(view: "form", model: [componenteInstance: componenteInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'componente.label', default: 'Componente'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "componente.create", default: "Create Componente")
            def componenteInstance = new Componente(params)
            if (componenteInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'componente.label', default: 'Componente'), componenteInstance.id])}"
                redirect(action: "show", id: componenteInstance.id)
            }
            else {
                render(view: "form", model: [componenteInstance: componenteInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción
     */
    def update = {
        def componenteInstance = Componente.get(params.id)
        if (componenteInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (componenteInstance.version > version) {

                    componenteInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'componente.label', default: 'Componente')] as Object[], "Another user has updated this Componente while you were editing")
                    render(view: "edit", model: [componenteInstance: componenteInstance])
                    return
                }
            }
            componenteInstance.properties = params
            if (!componenteInstance.hasErrors() && componenteInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'componente.label', default: 'Componente'), componenteInstance.id])}"
                redirect(action: "show", id: componenteInstance.id)
            }
            else {
                render(view: "edit", model: [componenteInstance: componenteInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'componente.label', default: 'Componente'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción
     */
    def show = {
        def componenteInstance = Componente.get(params.id)
        if (!componenteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'componente.label', default: 'Componente'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "componente.show", default: "Show Componente")

            [componenteInstance: componenteInstance, title: title]
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
        def componenteInstance = Componente.get(params.id)
        if (componenteInstance) {
            try {
                componenteInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'componente.label', default: 'Componente'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'componente.label', default: 'Componente'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'componente.label', default: 'Componente'), params.id])}"
            redirect(action: "list")
        }
    }
}
