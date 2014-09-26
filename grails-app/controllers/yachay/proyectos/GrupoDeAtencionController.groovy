package yachay.proyectos

import yachay.proyectos.GrupoDeAtencion

/**
 * Controlador
 */
class GrupoDeAtencionController {

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
        def title = g.message(code: "default.list.label", args: ["GrupoDeAtencion"], default: "GrupoDeAtencion List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [grupoDeAtencionInstanceList: GrupoDeAtencion.list(params), grupoDeAtencionInstanceTotal: GrupoDeAtencion.count(), title: title, params: params]
    }

    /**
     * Acción
     */
    def form = {
        def title
        def grupoDeAtencionInstance

        if (params.source == "create") {
            grupoDeAtencionInstance = new GrupoDeAtencion()
            grupoDeAtencionInstance.properties = params
            title = g.message(code: "default.create.label", args: ["GrupoDeAtencion"], default: "crearGrupoDeAtencion")
        } else if (params.source == "edit") {
            grupoDeAtencionInstance = GrupoDeAtencion.get(params.id)
            if (!grupoDeAtencionInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "default.edit.label", args: ["GrupoDeAtencion"], default: "editarGrupoDeAtencion")
        }

        return [grupoDeAtencionInstance: grupoDeAtencionInstance, title: title, source: params.source]
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
            title = g.message(code: "default.edit.label", args: ["GrupoDeAtencion"], default: "Edit GrupoDeAtencion")
            def grupoDeAtencionInstance = GrupoDeAtencion.get(params.id)
            if (grupoDeAtencionInstance) {
                grupoDeAtencionInstance.properties = params
                if (!grupoDeAtencionInstance.hasErrors() && grupoDeAtencionInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), grupoDeAtencionInstance.id])}"
                    redirect(action: "show", id: grupoDeAtencionInstance.id)
                }
                else {
                    render(view: "form", model: [grupoDeAtencionInstance: grupoDeAtencionInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "default.create.label", args: ["GrupoDeAtencion"], default: "Create GrupoDeAtencion")
            def grupoDeAtencionInstance = new GrupoDeAtencion(params)
            if (grupoDeAtencionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), grupoDeAtencionInstance.id])}"
                redirect(action: "show", id: grupoDeAtencionInstance.id)
            }
            else {
                render(view: "form", model: [grupoDeAtencionInstance: grupoDeAtencionInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción
     */
    def update = {
        def grupoDeAtencionInstance = GrupoDeAtencion.get(params.id)
        if (grupoDeAtencionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (grupoDeAtencionInstance.version > version) {

                    grupoDeAtencionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion')] as Object[], "Another user has updated this GrupoDeAtencion while you were editing")
                    render(view: "edit", model: [grupoDeAtencionInstance: grupoDeAtencionInstance])
                    return
                }
            }
            grupoDeAtencionInstance.properties = params
            if (!grupoDeAtencionInstance.hasErrors() && grupoDeAtencionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), grupoDeAtencionInstance.id])}"
                redirect(action: "show", id: grupoDeAtencionInstance.id)
            }
            else {
                render(view: "edit", model: [grupoDeAtencionInstance: grupoDeAtencionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción
     */
    def show = {
        def grupoDeAtencionInstance = GrupoDeAtencion.get(params.id)
        if (!grupoDeAtencionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "default.show.label", args: ["GrupoDeAtencion"], default: "Show GrupoDeAtencion")

            [grupoDeAtencionInstance: grupoDeAtencionInstance, title: title]
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
        def grupoDeAtencionInstance = GrupoDeAtencion.get(params.id)
        if (grupoDeAtencionInstance) {
            try {
                grupoDeAtencionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion'), params.id])}"
            redirect(action: "list")
        }
    }
}
