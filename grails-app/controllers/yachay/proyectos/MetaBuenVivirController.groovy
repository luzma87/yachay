package yachay.proyectos

import yachay.proyectos.MetaBuenVivir

/**
 * Controlador
 */
class MetaBuenVivirController {

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
        def title = g.message(code: "metabuenvivir.list", default: "MetaBuenVivir List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [metaBuenVivirInstanceList: MetaBuenVivir.list(params), metaBuenVivirInstanceTotal: MetaBuenVivir.count(), title: title, params: params]
    }

    /**
     * Acción
     */
    def form = {
        def title
        def metaBuenVivirInstance

        if (params.source == "create") {
            metaBuenVivirInstance = new MetaBuenVivir()
            metaBuenVivirInstance.properties = params
            title = g.message(code: "metabuenvivir.create", default: "Create MetaBuenVivir")
        } else if (params.source == "edit") {
            metaBuenVivirInstance = MetaBuenVivir.get(params.id)
            if (!metaBuenVivirInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "metabuenvivir.edit", default: "Edit MetaBuenVivir")
        }

        return [metaBuenVivirInstance: metaBuenVivirInstance, title: title, source: params.source]
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
            title = g.message(code: "metabuenvivir.edit", default: "Edit MetaBuenVivir")
            def metaBuenVivirInstance = MetaBuenVivir.get(params.id)
            if (metaBuenVivirInstance) {
                metaBuenVivirInstance.properties = params
                if (!metaBuenVivirInstance.hasErrors() && metaBuenVivirInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), metaBuenVivirInstance.id])}"
                    redirect(action: "show", id: metaBuenVivirInstance.id)
                }
                else {
                    render(view: "form", model: [metaBuenVivirInstance: metaBuenVivirInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "metabuenvivir.create", default: "Create MetaBuenVivir")
            def metaBuenVivirInstance = new MetaBuenVivir(params)
            if (metaBuenVivirInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), metaBuenVivirInstance.id])}"
                redirect(action: "show", id: metaBuenVivirInstance.id)
            }
            else {
                render(view: "form", model: [metaBuenVivirInstance: metaBuenVivirInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción
     */
    def update = {
        def metaBuenVivirInstance = MetaBuenVivir.get(params.id)
        if (metaBuenVivirInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (metaBuenVivirInstance.version > version) {

                    metaBuenVivirInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir')] as Object[], "Another user has updated this MetaBuenVivir while you were editing")
                    render(view: "edit", model: [metaBuenVivirInstance: metaBuenVivirInstance])
                    return
                }
            }
            metaBuenVivirInstance.properties = params
            if (!metaBuenVivirInstance.hasErrors() && metaBuenVivirInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), metaBuenVivirInstance.id])}"
                redirect(action: "show", id: metaBuenVivirInstance.id)
            }
            else {
                render(view: "edit", model: [metaBuenVivirInstance: metaBuenVivirInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción
     */
    def show = {
        def metaBuenVivirInstance = MetaBuenVivir.get(params.id)
        if (!metaBuenVivirInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "metabuenvivir.show", default: "Show MetaBuenVivir")

            [metaBuenVivirInstance: metaBuenVivirInstance, title: title]
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
        def metaBuenVivirInstance = MetaBuenVivir.get(params.id)
        if (metaBuenVivirInstance) {
            try {
                metaBuenVivirInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir'), params.id])}"
            redirect(action: "list")
        }
    }
}
